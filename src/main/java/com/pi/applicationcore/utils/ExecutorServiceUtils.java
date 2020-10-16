package com.pi.applicationcore.utils;

import com.pi.applicationcore.thread.PiCalculationThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceUtils {


    public static void shutdownPool(ThreadPoolExecutor executor, boolean awaitTermination) throws InterruptedException {

        //call shutdown to prevent new tasks from being submitted
        executor.shutdown();

        //get a reference to the Queue
        final BlockingQueue<Runnable> blockingQueue = executor.getQueue();

        //clear the Queue
        blockingQueue.clear();
        //or else copy its contents here with a while loop and remove()

        //wait for active tasks to be completed
        if (awaitTermination) {
            executor.awaitTermination(1000000, TimeUnit.SECONDS);
        }
    }
}
