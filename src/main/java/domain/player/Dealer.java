package domain.player;

import domain.card.Card;
import domain.card.Cards;

public final class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int FIRST = 0;
    private static final int STAY_NUMBER = 17;

    private Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer create(final int score) {
        return new Dealer(Name.of(DEALER_NAME), Cards.from(score));
    }

    public Card getFirstCard() {
        return getCards().get(FIRST);
    }

    public boolean isHit() {
        return getScore() < STAY_NUMBER;
    }
}
