package blackjack.domain.player;

import static blackjack.domain.Rule.DEALER_HIT_STANDARD_SCORE;

import blackjack.domain.card.Card;

public class Dealer extends Player {

    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public Card openFirstCard() {
        return getCards().iterator().next();
    }

    @Override
    public boolean canTakeCard() {
        return getTotalScore() <= DEALER_HIT_STANDARD_SCORE.getValue();
    }
}
