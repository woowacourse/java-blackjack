package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {
    public static final int HIT_UPPER_BOUND = 16;
    private static final String name = "딜러";

    public Dealer() {
        super();
    }

    @Override
    public boolean canContinue() {
        return getScore() <= HIT_UPPER_BOUND;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return super.getCards();
    }

    public Card getFirstCard() {
        return hand.getCard(0);
    }
}
