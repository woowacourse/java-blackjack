package blackjack.domain.gamer;

import blackjack.domain.card.Cards;

public class Player extends Gamer {

    private static final int DRAWABLE_NUMBER = 21;

    public Player(final String name, final Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean canDraw() {
        return calculateScore() <= DRAWABLE_NUMBER;
    }
}
