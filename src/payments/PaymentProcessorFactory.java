package payments;

public class PaymentProcessorFactory {

    private PaymentProcessorFactory() {}

    public static PaymentProcessor getCardBasedPaymentProcessor(double amouont, CardDetails cardDetails){
        return new CardPaymentProcessor(amouont, cardDetails);
    }

    public static PaymentProcessor getCashBasedPaymentProcessor(double amount){
        return new CashPaymentProcessor(amount);
    }
}
