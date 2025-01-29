package payments;

public class CardPaymentProcessor implements PaymentProcessor {

    private final double amount;
    private final CardDetails cardDetails;

    @Override
    public double getAmount() {
        return 0;
    }

    public CardPaymentProcessor(double amount, CardDetails cardDetails) {
        this.amount = amount;
        this.cardDetails = cardDetails;
    }

    @Override
    public boolean executePayment() {
        return false;
    }
}
