package participant;

import card.Card;

public class Dealer extends Participant {

    private static final int DEALER_CARDS_MIN_TOTAL = 17;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canReceiveCard() {
        return score() < DEALER_CARDS_MIN_TOTAL;
    }

    public Card getLastCard() {
        return getCards().getLast();
    }
}
