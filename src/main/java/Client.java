public class Client implements Runnable {

    private Request request;
    private FrontalSystem frontalSystem;


    public Client(Request request, FrontalSystem frontalSystem) {

        this.request = request;
        this.frontalSystem = frontalSystem;
    }


    @Override
    public void run() {
        frontalSystem.addRequest(request);
        System.out.println("Заявка " + request.toString() + " отправлена в банк");
    }


}
