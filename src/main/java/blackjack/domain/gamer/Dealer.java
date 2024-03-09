package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {
    public static final int HIT_UPPER_BOUND = 16;
    private static final String name = "딜러";

    public Dealer() {
        super(name);
    }

    public Card getFirstCard() {
        return deck.get().get(0);
    }

    public boolean isHitUnderBound() {
        return deck.totalScore() <= HIT_UPPER_BOUND;
    }
}
