package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Player {
    public static final int HIT_UPPER_BOUND = 16;
    private static final String name = "딜러";

    public Dealer() {
        super(name);
    }

    @Override
    public List<Card> getCards() {
        if (isOnlyDeal()) {
            return super.getCards().subList(0, 1);
        }
        return super.getCards();
    }

    public boolean isHitUnderBound() {
        return cards.totalScore() <= HIT_UPPER_BOUND;
    }
}
