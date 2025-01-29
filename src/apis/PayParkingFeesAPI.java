package apis;

import data.PaymentMode;
import data.Ticket;
import payments.CardDetails;
import payments.ParkingFeeProcessor;
import payments.PaymentProcessor;
import payments.PaymentProcessorFactory;

import java.util.Map;

public class PayParkingFeesAPI {

    public boolean payParkingFee(Ticket ticket, PaymentMode paymentMode,
                                 Map<String, String> paymentDetails) {
        PaymentProcessor paymentProcessor = null;
        if (paymentMode.equals(PaymentMode.CARD)) {
            double amount = Double.parseDouble(paymentDetails.get("amount"));
            CardDetails cardDetails = null;
            //logic to create above object
            paymentProcessor = PaymentProcessorFactory.getCardBasedPaymentProcessor(amount, cardDetails);
        } else if (paymentMode.equals(PaymentMode.CASH)) {
            double amount = Double.parseDouble(paymentDetails.get("amount"));
            paymentProcessor = PaymentProcessorFactory.getCashBasedPaymentProcessor(amount);
        } else {
            throw new IllegalArgumentException("Unsupported payment mode: " + paymentMode);
        }
        return new ParkingFeeProcessor().processParkingFees(ticket, paymentProcessor);
    }

}
