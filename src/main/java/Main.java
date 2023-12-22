import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FrontalSystem frontalSystem = new FrontalSystem();

        Request request1 = new Request("Клиент_1", 10, OperationType.INCREASE);
        Request request2 = new Request("Клиент_2", 20, OperationType.DECREASE);
        Request request3 = new Request("Клиент_3", 30, OperationType.INCREASE);
        Request request4 = new Request("Клиент_4", 100, OperationType.DECREASE);

        Client client1 = new Client(request1, frontalSystem);
        Client client2 = new Client(request2, frontalSystem);
        Client client3 = new Client(request3, frontalSystem);

        Client client4 = new Client(request4, frontalSystem);
        Bank bank = new Bank(5);

        ExecutorService es = Executors.newFixedThreadPool(1);
        Warm_up warmUp = new Warm_up(1200,1000,bank);
        Future<Integer> sub = es.submit(warmUp);
        Integer some = sub.get();
        es.shutdown();


        ExecutorService poolClientsThread = Executors.newFixedThreadPool(3);
        poolClientsThread.execute(client1);
        poolClientsThread.execute(client2);
        poolClientsThread.execute(client3);
        poolClientsThread.execute(client4);



        RequestHandler requestHandler1 = new RequestHandler("Обработчик заявок №1", bank, frontalSystem);
        RequestHandler requestHandler2 = new RequestHandler("Обработчик заявок №2", bank, frontalSystem);

        ExecutorService poolHandlersThread = Executors.newFixedThreadPool(1);
        poolHandlersThread.execute(requestHandler1);
        poolHandlersThread.execute(requestHandler2);

    }
}