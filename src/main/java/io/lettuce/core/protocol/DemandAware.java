package io.lettuce.core.protocol;

/**
 * Interface for demand-aware components.
 * <p>
 * A demand-aware component is aware of its demand for data that is read from the {@link Source} and possibly awaits processing.
 * A {@link Sink} with demand is ready to process data. A {@link Sink} without demand signals that it's ability to keep up with
 * the incoming data is no longer given and it wishes to receive no more data. Submitting more data could cause overload and
 * exhaust buffer space.
 *
 * @author Mark Paluch
 * @since 5.0
 */
public interface DemandAware {

    /**
     * A demand-aware {@link Sink} that accepts data. It can signal its {@link Source} demand/readiness to emit more data.
     * Instances of implementing classes are required to be thread-safe as they are shared amongst multiple threads.
     */
    interface Sink {

        /**
         * Returns {@code true} if the {@link Sink} has demand or {@code false} if the source has no demand. {@code false} means
         * either the {@link Sink} has no demand in general because data is not needed or the current demand is saturated.
         *
         * @return {@code true} if the {@link Sink} demands data.
         */
        boolean hasDemand();

        /**
         * Sets the {@link Source} for a {@link Sink}. The {@link Sink} is notified by this {@link Source} if the source
         * indicates new demand or the sink catches up so it's ready to receive more data.
         *
         * @param source the reference to the data {@link Source}, must not be {@code null}.
         */
        void setSource(Source source);

        /**
         * Removes the {@link Source} reference from this {@link Sink}. Any previously set {@link Source} will no longer be
         * asked for data.
         */
        void removeSource();

    }

    /**
     * A {@link Source} provides data to a {@link DemandAware} and can be notified to produce more input for the command.
     * Instances of implementing classes are required to be thread-safe as they are shared amongst multiple threads.
     */
    interface Source {

        /**
         * Signals demand to the {@link Source}
         */
        void requestMore();

    }

}
