package domain.participant;

import domain.card.Cards;

public class Player extends Participant {

    private Player(final Name name) {
        super(name);
    }

    private Player(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Player of(final Name name) {
        return new Player(name);
    }

    public static Player create(final Name name, final Cards cards) {
        return new Player(name, cards);
    }
}
