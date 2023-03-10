package balckjack.domain;

public class Dealer extends Participant {

    private static final int DEALER_HIT_NUMBER = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isContinueDealerTurn() {
        return getCardDeck().calculateScore().getValue() <= DEALER_HIT_NUMBER;
    }
}
