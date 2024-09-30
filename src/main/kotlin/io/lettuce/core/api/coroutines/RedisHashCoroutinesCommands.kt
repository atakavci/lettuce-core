/*
 * Copyright 2017-Present, Redis Ltd. and Contributors
 * All rights reserved.
 *
 * Licensed under the MIT License.
 *
 * This file contains contributions from third-party contributors
 * licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.lettuce.core.api.coroutines

import io.lettuce.core.ExperimentalLettuceCoroutinesApi
import io.lettuce.core.ExpireArgs
import kotlinx.coroutines.flow.Flow
import io.lettuce.core.KeyScanCursor
import io.lettuce.core.KeyValue
import io.lettuce.core.MapScanCursor
import io.lettuce.core.ScanArgs
import io.lettuce.core.ScanCursor
import java.time.Duration
import java.time.Instant
import java.util.*

/**
 * Coroutine executed commands for Hashes (Key-Value pairs).
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mikhael Sokolov
 * @since 6.0
 * @generated by io.lettuce.apigenerator.CreateKotlinCoroutinesApi
 */
@ExperimentalLettuceCoroutinesApi
interface RedisHashCoroutinesCommands<K : Any, V : Any> {

    /**
     * Delete one or more hash fields.
     *
     * @param key the key.
     * @param fields the field type: key.
     * @return Long integer-reply the number of fields that were removed from the hash, not including specified but non existing
     *         fields.
     */
    suspend fun hdel(key: K, vararg fields: K): Long?

    /**
     * Determine if a hash field exists.
     *
     * @param key the key.
     * @param field the field type: key.
     * @return Boolean integer-reply specifically:
     *
     *         `true` if the hash contains `field`. `false` if the hash does not contain `field`, or
     *         `key` does not exist.
     */
    suspend fun hexists(key: K, field: K): Boolean?

    /**
     * Get the value of a hash field.
     *
     * @param key the key.
     * @param field the field type: key.
     * @return V bulk-string-reply the value associated with `field`, or `null` when `field` is not present in
     *         the hash or `key` does not exist.
     */
    suspend fun hget(key: K, field: K): V?

    /**
     * Increment the integer value of a hash field by the given number.
     *
     * @param key the key.
     * @param field the field type: key.
     * @param amount the increment type: long.
     * @return Long integer-reply the value at `field` after the increment operation.
     */
    suspend fun hincrby(key: K, field: K, amount: Long): Long?

    /**
     * Increment the float value of a hash field by the given amount.
     *
     * @param key the key.
     * @param field the field type: key.
     * @param amount the increment type: Double.
     * @return Double bulk-string-reply the value of `field` after the increment.
     */
    suspend fun hincrbyfloat(key: K, field: K, amount: Double): Double?

    /**
     * Get all the fields and values in a hash.
     *
     * @param key the key.
     * @return Map<K,V> array-reply list of fields and their values stored in the hash, or an empty list when `key`
     *         does not exist.
     */
    fun hgetall(key: K): Flow<KeyValue<K, V>>

    /**
     * Get all the fields in a hash.
     *
     * @param key the key.
     * @return List<K> array-reply list of fields in the hash, or an empty list when `key` does not exist.
     */
    fun hkeys(key: K): Flow<K>

    /**
     * Get the number of fields in a hash.
     *
     * @param key the key.
     * @return Long integer-reply number of fields in the hash, or `0` when `key` does not exist.
     */
    suspend fun hlen(key: K): Long?

    /**
     * Get the values of all the given hash fields.
     *
     * @param key the key.
     * @param fields the field type: key.
     * @return List<V> array-reply list of values associated with the given fields, in the same.
     */
    fun hmget(key: K, vararg fields: K): Flow<KeyValue<K, V>>

    /**
     * Set multiple hash fields to multiple values.
     *
     * @param key the key.
     * @param map the hash to apply.
     * @return String simple-string-reply.
     */
    suspend fun hmset(key: K, map: Map<K, V>): String?

    /**
     * Return a random field from the hash stored at `key`.
     *
     * @param key the key.
     * @return hash field name.
     * @since 6.1
     */
    suspend fun hrandfield(key: K): K?

    /**
     * Return `count` random fields from the hash stored at `key`.
     *
     * @param key the key.
     * @param count the number of fields to return. If the provided count argument is positive, return an array of distinct fields.
     * @return array-reply list of field names.
     * @since 6.1
     */
    suspend fun hrandfield(key: K, count: Long): List<K>

    /**
     * Return a random field along its value from the hash stored at `key`.
     *
     * @param key the key.
     * @return array-reply the key and value.
     * @since 6.1
     */
    suspend fun hrandfieldWithvalues(key: K): KeyValue<K, V>?

    /**
     * Return `count` random fields along their value from the hash stored at `key`.
     *
     * @param key the key.
     * @param count the number of fields to return. If the provided count argument is positive, return an array of distinct fields.
     * @return array-reply the keys and values.
     * @since 6.1
     */
    suspend fun hrandfieldWithvalues(key: K, count: Long): List<KeyValue<K, V>>

    /**
     * Incrementally iterate hash fields and associated values.
     *
     * @param key the key.
     * @return MapScanCursor<K, V> map scan cursor.
     */
    suspend fun hscan(key: K): MapScanCursor<K, V>?

    /**
     * Incrementally iterate hash fields, without associated values.
     *
     * @param key the key.
     * @return KeyScanCursor<K> key scan cursor.
     * @since 6.4
     */
    suspend fun hscanNovalues(key: K): KeyScanCursor<K>?

    /**
     * Incrementally iterate hash fields and associated values.
     *
     * @param key the key.
     * @param scanArgs scan arguments.
     * @return MapScanCursor<K, V> map scan cursor.
     */
    suspend fun hscan(key: K, scanArgs: ScanArgs): MapScanCursor<K, V>?

    /**
     * Incrementally iterate hash fields, without associated values.
     *
     * @param key the key.
     * @param scanArgs scan arguments.
     * @return KeyScanCursor<K> key scan cursor.
     * @since 6.4
     */
    suspend fun hscanNovalues(key: K, scanArgs: ScanArgs): KeyScanCursor<K>?

    /**
     * Incrementally iterate hash fields and associated values.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be `null`.
     * @param scanArgs scan arguments.
     * @return MapScanCursor<K, V> map scan cursor.
     */
    suspend fun hscan(key: K, scanCursor: ScanCursor, scanArgs: ScanArgs): MapScanCursor<K, V>?

    /**
     * Incrementally iterate hash fields, without associated values.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be `null`.
     * @param scanArgs scan arguments.
     * @return KeyScanCursor<K> key scan cursor.
     * @since 6.4
     */
    suspend fun hscanNovalues(key: K, scanCursor: ScanCursor, scanArgs: ScanArgs): KeyScanCursor<K>?

    /**
     * Incrementally iterate hash fields and associated values.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be `null`.
     * @return MapScanCursor<K, V> map scan cursor.
     */
    suspend fun hscan(key: K, scanCursor: ScanCursor): MapScanCursor<K, V>?

    /**
     * Incrementally iterate hash fields, without associated values.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be `null`.
     * @return KeyScanCursor<K> key scan cursor.
     * @since 6.4
     */
    suspend fun hscanNovalues(key: K, scanCursor: ScanCursor): KeyScanCursor<K>?

    /**
     * Set the string value of a hash field.
     *
     * @param key the key.
     * @param field the field type: key.
     * @param value the value.
     * @return Boolean integer-reply specifically:
     *
     *         `true` if `field` is a new field in the hash and `value` was set. `false` if
     *         `field` already exists in the hash and the value was updated.
     */
    suspend fun hset(key: K, field: K, value: V): Boolean?

    /**
     * Set multiple hash fields to multiple values.
     *
     * @param key the key of the hash.
     * @param map the field/value pairs to update.
     * @return Long integer-reply: the number of fields that were added.
     * @since 5.3
     */
    suspend fun hset(key: K, map: Map<K, V>): Long?

    /**
     * Set the value of a hash field, only if the field does not exist.
     *
     * @param key the key.
     * @param field the field type: key.
     * @param value the value.
     * @return Boolean integer-reply specifically:
     *
     *         `1` if `field` is a new field in the hash and `value` was set. `0` if `field`
     *         already exists in the hash and no operation was performed.
     */
    suspend fun hsetnx(key: K, field: K, value: V): Boolean?

    /**
     * Get the string length of the field value in a hash.
     *
     * @param key the key.
     * @param field the field type: key.
     * @return Long integer-reply the string length of the `field` value, or `0` when `field` is not present
     *         in the hash or `key` does not exist at all.
     */
    suspend fun hstrlen(key: K, field: K): Long?

    /**
     * Get all the values in a hash.
     *
     * @param key the key.
     * @return List<V> array-reply list of values in the hash, or an empty list when `key` does not exist.
     */
    fun hvals(key: K): Flow<V>

    /**
     * Set the time to live (in seconds) for one or more fields, belonging to a certain key.
     *
     * @param key the key of the fields.
     * @param seconds the seconds type: long.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is 0; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpire(key: K, seconds: Long, vararg fields: K): List<Long>

    /**
     * Set the time to live (in seconds) for one or more fields, belonging to a certain key.
     *
     * @param key the key of the fields.
     * @param seconds the seconds type: long.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is 0; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpire(key: K, seconds: Long, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields, belonging to a certain key.
     *
     * @param key the key.
     * @param seconds the TTL [Duration]
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is 0; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpire(key: K, seconds: Duration, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields, belonging to a certain key.
     *
     * @param key the key.
     * @param seconds the TTL [Duration]
     * @param expireArgs the [ExpireArgs].
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is 0; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpire(key: K, seconds: Duration, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields, belonging to a certain key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpireat(key: K, timestamp: Long, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields, belonging to a certain key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpireat(key: K, timestamp: Long, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields, belonging to a certain key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpireat(key: K, timestamp: Date, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields, belonging to a certain key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpireat(key: K, timestamp: Date, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields, belonging to a certain key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpireat(key: K, timestamp: Instant, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields, belonging to a certain key as a UNIX timestamp.
     *
     * @param key the key.
     * @param timestamp the timestamp type: posix time.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpireat(key: K, timestamp: Instant, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Get the time to live for one or more fields in as UNIX timestamp in seconds.
     *
     * @param key the key.
     * @param fields one or more fields to get the TTL for.
     * @return a list of [Long] values for each of the fields provided: expiration time as a UNIX timestamp in seconds;
     *         `-1` indicating the field has no expiry time set; `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hexpiretime(key: K, vararg fields: K): List<Long>

    /**
     * Remove the expiration from one or more fields.
     *
     * @param key the key.
     * @param fields one or more fields to remove the TTL for.
     * @return a list of [Long] values for each of the fields provided: `1` indicating expiration time is removed;
     *         `-1` field has no expiration time to be removed; `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpersist(key: K, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields in milliseconds.
     *
     * @param key the key.
     * @param milliseconds the milliseconds type: long.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is 0; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpire(key: K, milliseconds: Long, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields in milliseconds.
     *
     * @param key the key.
     * @param milliseconds the milliseconds type: long.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is 0; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpire(key: K, milliseconds: Long, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields in milliseconds.
     *
     * @param key the key.
     * @param milliseconds the milliseconds.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is 0; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpire(key: K, milliseconds: Duration, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields in milliseconds.
     *
     * @param key the key.
     * @param milliseconds the milliseconds.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is 0; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpire(key: K, milliseconds: Duration, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpireat(key: K, timestamp: Long, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpireat(key: K, timestamp: Long, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpireat(key: K, timestamp: Date, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpireat(key: K, timestamp: Date, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpireat(key: K, timestamp: Instant, vararg fields: K): List<Long>

    /**
     * Set the time to live for one or more fields as a UNIX timestamp specified in milliseconds.
     *
     * @param key the key.
     * @param timestamp the milliseconds-timestamp type: posix time.
     * @param expireArgs the expire arguments.
     * @param fields one or more fields to set the TTL for.
     * @return a list of [Long] values for each of the fields provided: `2` indicating the specific field is deleted
     *         already due to expiration, or provided expriry interval is in the past; `1` indicating expiration time is
     *         set/updated; `0` indicating the expiration time is not set (a provided NX | XX | GT | LT condition is not
     *         met); `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpireat(key: K, timestamp: Instant, expireArgs: ExpireArgs, vararg fields: K): List<Long>

    /**
     * Get the time to live for one or more fields as UNIX timestamp in milliseconds.
     *
     * @param key the key.
     * @param fields one or more fields to get the TTL for.
     * @return a list of [Long] values for each of the fields provided: expiration time as a UNIX timestamp in milliseconds;
     *         `-1` indicating the field has no expiry time set; `-2` indicating there is no such field
     * @since 6.4
     */
    suspend fun hpexpiretime(key: K, vararg fields: K): List<Long>

    /**
     * Get the time to live for one or more fields.
     *
     * @param key the key.
     * @param fields one or more fields to get the TTL for.
     * @return a list of [Long] values for each of the fields provided: the time to live in seconds; or a negative value in
     *         order to signal an error. The command returns `-1` if the key exists but has no associated expiration time.
     *         The command returns `-2` if the key does not exist.
     * @since 6.4
     */
    suspend fun httl(key: K, vararg fields: K): List<Long>

    /**
     * Get the time to live for one or more fields in milliseconds.
     *
     * @param key the key.
     * @param fields one or more fields to get the TTL for.
     * @return a list of [Long] values for each of the fields provided: the time to live in milliseconds; or a negative
     *         value in order to signal an error. The command returns `-1` if the key exists but has no associated
     *         expiration time. The command returns `-2` if the key does not exist.
     * @since 6.4
     */
    suspend fun hpttl(key: K, vararg fields: K): List<Long>

}

