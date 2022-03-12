package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.Lose;
import blackjack.domain.result.Win;

import java.util.List;

public class Dealer extends Player {

    private static final int ADD_CARD_CONDITION = 16;
    private static final int FIRST = 0;
    private static final String DEALER_NAME = "딜러";

    public Dealer(final List<Card> cards) {
        super(cards, DEALER_NAME);
    }

    public boolean acceptableCard() {
        return cards.calculateScoreByAceEleven() <= ADD_CARD_CONDITION;
    }

    public Card getCardFirstOne() {
        return getCards().get(FIRST);
    }
}
