package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;

public class Dealer extends Gamer{

    private static final String NAME = "딜러";
    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int DEALER_OPEN_COUNT_FIRST = 1;

    public Dealer() {
        super(NAME);
    }

    public int findHitCount() {
        return getCards().size() - BLACKJACK_CARD_COUNT;
    }

    public List<Card> openCardFirst() {
        return cards.open(DEALER_OPEN_COUNT_FIRST);
    }
}
