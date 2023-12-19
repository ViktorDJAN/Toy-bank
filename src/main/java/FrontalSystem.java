import java.util.*;

public class FrontalSystem {

    private Queue<Request> requestsQueue = new LinkedList<>();

    public Queue<Request> getRequestsQueue() {
        return requestsQueue;
    }

    public void addRequest(Request request) {
        synchronized (requestsQueue) {
            while (requestsQueue.size() > 2) {
                try {
                    requestsQueue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            requestsQueue.add(request);
            requestsQueue.notifyAll();
        }
    }


    public Request getRequest() {
        synchronized (requestsQueue) {
            while (requestsQueue.size() <1) {
                try {
                    requestsQueue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
                 Request request1 = requestsQueue.poll();
       //         System.out.println("заявка от " + request1.getClientName() + " была отправлен обработчику");
                requestsQueue.notifyAll();

            return request1;
        }
    }

}

