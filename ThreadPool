// "static void main" must be defined in a public class.
// Implement simple ThreadPool
class ThreadPool {
    private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedBlockingQueue<Runnable> queue;
    
    public ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedBlockingQueue<Runnable>();
        threads = new PoolWorker[nThreads];
        
        for (int i = 0; i < nThreads; ++i) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }
    
    public void execute(Runnable task) {
        synchronized (queue) {
            queue.offer(task);
            queue.notify();
        }
    }
    
    private class PoolWorker extends Thread {
        public void run() {
            Runnable task;
            
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try{
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting : " + e.getMessage());
                        }
                    }
                    task = queue.poll();
                }
                
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue : " + e.getMessage());
                }
            }
        }
    }
}

class Task implements Runnable {
    private int num;
    public Task(int n) {
        num = n;
    }
    
    public void run() {
        System.out.println("Task " + num + " is running.");
        for (int i = 0; i < 10000; ++i) {
            int a = 0;
        }
        System.out.println("Task " + num + " is end.");
    }
}
public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(5);
        for (int i = 0; i < 10; ++i) {
            Task task = new Task(i);
            pool.execute(task);
        }
    }
}
