package com.mihanjk.domain.async;

import java.util.concurrent.*;

public class Executor {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public <T> T executeCallable(Callable<T> task) {
        try {
            return executor.submit(task).get(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Execution of operation was interrupted");
        } catch (ExecutionException e) {
            System.err.println("Execution of operation was failed");
        } catch (TimeoutException e) {
            System.err.println("Task were executed more than 10 seconds, " +
                    "check your internet connection");
        }
        return null;
    }

    public void executeRunnable(Runnable task) {
        executor.submit(task);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
