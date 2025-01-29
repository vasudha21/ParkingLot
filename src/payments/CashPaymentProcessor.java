package payments;

public class CashPaymentProcessor implements PaymentProcessor {

    private final double amount;

    @Override
    public double getAmount() {
        return 0;
    }

    public CashPaymentProcessor(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean executePayment() {
        return false;
    }
}
