public class RequestHandler implements Runnable {
    private String handlerName;
    private Bank bank;
    private FrontalSystem frontalSystem;

    @Override
    public void run() {
        while (frontalSystem.getRequestsQueue().size() > 0) {
            Request request = frontalSystem.getRequest();
            System.out.println(handlerName + ": получена заявка на обработку по клиенту - " + request.getClientName());
            if (request.getOperationType().equals(OperationType.INCREASE)) {
                bank.increaseTotalAmount(request.getAmount(),request);
            }
            if (request.getOperationType().equals(OperationType.DECREASE)) {
                bank.decreaseTotalAmount((request.getAmount()),request);
            }

        }
    }

    public RequestHandler(String handlerName,Bank bank, FrontalSystem frontalSystem) {
        this.handlerName = handlerName;
        this.bank = bank;
        this.frontalSystem = frontalSystem;
    }

}
