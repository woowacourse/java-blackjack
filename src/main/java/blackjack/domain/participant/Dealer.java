package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int SCORE_LOWER_BOUND = 17;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean canDrawCard() {
        return getScore() < SCORE_LOWER_BOUND;
    }

    public Card openFirstCard() {
        return getCards().findFirst();
    }
}
