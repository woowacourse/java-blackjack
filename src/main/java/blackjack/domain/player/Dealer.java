package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {

    private static final int ADD_CARD_CONDITION = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean acceptableCard() {
        return cards.calculateScoreByAceEleven() <= ADD_CARD_CONDITION;
    }
}
