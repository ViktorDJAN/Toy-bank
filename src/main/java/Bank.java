public class Bank {

    private int totalAmount;

    public Bank(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }


    public synchronized void increaseTotalAmount(int value, Request request) {
        totalAmount += value;
        printInfoPositive(request);
    }

    public synchronized void decreaseTotalAmount(int value, Request request) {
        if (totalAmount < value) {
            printInfoNegative(request);
        } else {
            totalAmount -= value;
            printInfoPositive(request);
        }
    }

    public void printInfoPositive(Request request) {
        System.out.println("Бэк система: Заявка по клиенту " + request.getClientName() + ", сумма = " + request.getAmount()
                + " тип операции = " + request.getOperationType() + " УСПЕШНО ВЫПОЛНЕНА. Баланс банка: " + totalAmount);
    }

    public void printInfoNegative(Request request) {
        System.out.println("Бэк система: Заявка по клиенту " + request.getClientName() + ", сумма = " + request.getAmount()
                + " тип операции = " + request.getOperationType() + " НЕ ВЫПОЛНЕНА. Баланс банка: " + totalAmount);
    }


}
