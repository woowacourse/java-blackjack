package domain.player;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public final class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int FIRST = 0;
    private static final int STAY_SCORE = 17;

    private Dealer(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Dealer create(final int score) {
        return new Dealer(Name.of(DEALER_NAME), Hand.from(score));
    }

    @Override
    public List<Card> showCards() {
        return List.of(getCards().get(FIRST));
    }

    @Override
    public boolean canHit() {
        return score().isUnderThan(STAY_SCORE);
    }
}
