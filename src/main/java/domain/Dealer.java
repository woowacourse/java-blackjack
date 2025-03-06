package domain;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";
    public static final int THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card openOneCard() {
        return hand.getFirstCard();
    }

    public boolean isBelowThreshold() {
        return getHandTotal() <= THRESHOLD;
    }

    public int getCardCount() {
        return hand.getSize();
    }
}
