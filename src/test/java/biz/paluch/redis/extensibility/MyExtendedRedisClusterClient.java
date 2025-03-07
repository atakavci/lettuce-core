/*
 * Copyright 2011-Present, Redis Ltd. and Contributors
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
package biz.paluch.redis.extensibility;

import java.time.Duration;
import java.util.function.Supplier;

import javax.enterprise.inject.Alternative;

import io.lettuce.core.RedisChannelWriter;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.ClusterPushHandler;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.StatefulRedisClusterConnectionImpl;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.json.JsonParser;
import io.lettuce.core.resource.ClientResources;
import reactor.core.publisher.Mono;

/**
 * Demo code for extending a RedisClusterClient.
 *
 * @author Julien Ruaux
 */
@Alternative
public class MyExtendedRedisClusterClient extends RedisClusterClient {

    public MyExtendedRedisClusterClient(ClientResources clientResources, Iterable<RedisURI> redisURIs) {
        super(clientResources, redisURIs);
    }

    public MyExtendedRedisClusterClient() {
    }

    @Override
    protected <V, K> StatefulRedisClusterConnectionImpl<K, V> newStatefulRedisClusterConnection(
            RedisChannelWriter channelWriter, ClusterPushHandler pushHandler, RedisCodec<K, V> codec, Duration timeout,
            Supplier<JsonParser> parser) {
        return new MyRedisClusterConnection<>(channelWriter, pushHandler, codec, timeout, parser);
    }

}
