package domain;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    public static final int ADDITIONAL_THRESHOLD = 16;

    public Dealer() {
        this.cards = new Cards();
    }

    public boolean needAdditionalCard() {
        return getCardsTotalSum() <= ADDITIONAL_THRESHOLD;
    }

    public String getFirstCardDisplay() {
        return cards.getFirstCard().getCardDisplay();
    }

    public String getName() {
        return DEALER_NAME;
    }

}
