package org.github.docent.kguava.ext

import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.ListeningExecutorService
import java.util.concurrent.TimeUnit
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListeningScheduledExecutorService


inline fun <I, O> ListenableFuture<I>.transform(executor: ListeningScheduledExecutorService,
                                                delay: Long, unit: TimeUnit, function: (I) -> O) =
        Futures.transform(this) { (input: I) ->
            executor.schedule({ () : O -> function(input)}, delay, unit)
        }

inline fun <I, O> ListenableFuture<I>.transform(executor: ListeningExecutorService,
                                                function: (I) -> O) =
        Futures.transform(this) { (input: I) ->
            executor.submit {() : O -> function(input) }
        }


