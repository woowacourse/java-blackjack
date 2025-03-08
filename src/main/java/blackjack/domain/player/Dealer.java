package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int DEALER_OPENED_CARD_LOWER_BOUND = 0;
    private static final int DEALER_OPENED_CARD_UPPER_BOUND = 1;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public List<Card> getOpenedCards() {
        return getCards().subList(DEALER_OPENED_CARD_LOWER_BOUND, DEALER_OPENED_CARD_UPPER_BOUND);
    }
}
