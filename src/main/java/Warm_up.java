import java.util.concurrent.*;
import java.util.function.Consumer;

public class Warm_up implements Callable {
    private int warmingTime;
    private int additionalAmount;
    private Bank bank;

    public Warm_up(int warmingTime, int additionalAmount, Bank bank) {
        this.warmingTime = warmingTime;
        this.additionalAmount = additionalAmount;
        this.bank = bank;
    }

    @Override
    public Void call() throws Exception {
        for (int i = 0; i < 2; i++) {
            Thread.sleep(warmingTime);
        }
        bank.setTotalAmount(bank.getTotalAmount() + additionalAmount);
        return null;
    }
}
