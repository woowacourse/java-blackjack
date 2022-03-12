package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;


public class Dealer extends Participant{

    public static final int BOUND_FOR_ADDITIONAL_CARD = 16;
    public static final String DEALER_DEFAULT_NAME = "딜러";
    private static final int OPEN_CARD_SIZE = 1;

    public Dealer(Cards cards) {
        super(DEALER_DEFAULT_NAME, cards);
    }

    public boolean doesNeedToDraw() {
        return super.getScore() <= BOUND_FOR_ADDITIONAL_CARD;
    }

    @Override
    public List<Card> openCards() {
        return getCards().subList(0, OPEN_CARD_SIZE);
    }
}
