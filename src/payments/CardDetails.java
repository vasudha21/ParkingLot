package payments;

public class CardDetails {
    private final String nameOnCard;
    private final String numberOnCard;
    private final int pin;

    public CardDetails(String nameOnCard, String numberOnCard, int pin) {
        this.nameOnCard = nameOnCard;
        this.numberOnCard = numberOnCard;
        this.pin = pin;
    }
}
