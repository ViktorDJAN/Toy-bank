public class Main {
    public static void main(String[] args) {
        FrontalSystem frontalSystem = new FrontalSystem();

        Request request1 = new Request("Клиент_1", 10, OperationType.INCREASE);
        Request request2 = new Request("Клиент_2", 20, OperationType.DECREASE);
        Request request3 = new Request("Клиент_3", 30, OperationType.INCREASE);
        Request request4 = new Request("Клиент_4", 100, OperationType.DECREASE);

        Client client1 = new Client(request1, frontalSystem);
        Client client2 = new Client(request2, frontalSystem);
        Client client3 = new Client(request3, frontalSystem);
        Client client4 = new Client(request4, frontalSystem);


        Thread thread1 = new Thread(client1);
        Thread thread2 = new Thread(client2);
        Thread thread3 = new Thread(client3);
        Thread thread4 = new Thread(client4);


        Bank bank = new Bank(5);

        RequestHandler requestHandler1 = new RequestHandler("Обработчик заявок №1", bank, frontalSystem);
        RequestHandler requestHandler2 = new RequestHandler("Обработчик заявок №2", bank, frontalSystem);

        Thread handler1 = new Thread(requestHandler1);
        Thread handler2 = new Thread(requestHandler2);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        handler1.start();
        handler2.start();


    }
}