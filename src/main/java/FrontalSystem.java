import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FrontalSystem {
    private final Lock lock = new ReentrantLock();
    /**
     * Выбрал  LinkedBlockingDeque так как :
     * класс имеет хорошую производительность при вставке и уадалении элементов
     * высокая степень параллелизма что дает нескольким потокам обращаться одновременно без блокировок
     */
    private BlockingQueue<Request> requestsQueue = new LinkedBlockingDeque<>();

    public void addRequest(Request request) {

        lock.lock();
        {
            while (requestsQueue.size() > 2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            requestsQueue.add(request);
            lock.unlock();
        }
    }


    public Request getRequest() {
        lock.lock();
        {
            while (requestsQueue.size() < 1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Request request1 = requestsQueue.poll();
            lock.unlock();

            return request1;
        }
    }

}

