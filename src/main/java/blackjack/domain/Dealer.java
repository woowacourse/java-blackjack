package blackjack.domain;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isContinueDealerTurn() {
        return getCardDeck().calculateScore().canDealerHit();
    }
}
