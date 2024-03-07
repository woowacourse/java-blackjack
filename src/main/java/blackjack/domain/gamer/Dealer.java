package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {
    private static final String name = "딜러";
    private static final int HIT_UPPER_BOUND = 16;


    public Dealer() {
        super(name);
    }

    public Card getFirstCard() {
        return cards.get().get(0);
    }

    public boolean isHitUnderBound() {
        return cards.totalScore() <= HIT_UPPER_BOUND;
    }
}
