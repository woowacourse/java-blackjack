package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;

import java.util.List;

public class Dealer extends User {

    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_SCORE_VALUE_LIMIT = 16;

    public Dealer(CardGroup initialGroup) {
        super(DEALER_NAME, initialGroup);
    }

    @Override
    public List<Card> getInitialHoldingCards() {
        return List.of(getHandholdingCards().get(0));
    }

    public boolean isOverDraw() {
        return this.getScore().getValue() > DEALER_DRAW_SCORE_VALUE_LIMIT;
    }
}
