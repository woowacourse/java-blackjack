package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int DEALER_OPENED_CARD_LOWER_BOUND = 0;
    private static final int DEALER_OPENED_CARD_UPPER_BOUND = 1;

    public Dealer() {
        super();
    }

    @Override
    public List<Card> getOpenedCards() {
        return getCards().subList(DEALER_OPENED_CARD_LOWER_BOUND, DEALER_OPENED_CARD_UPPER_BOUND);
    }
}
