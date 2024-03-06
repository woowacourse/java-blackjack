package blackjack.domain;

import java.util.ArrayList;

public class Player extends Participant {
    private final Name name;

    private Player(final Name name, final Cards cards) {
        super(cards);
        this.name = name;
    }

    public static Player from(final String name) {
        return new Player(new Name(name), new Cards(new ArrayList<>()));
    }

    public Name getName() {
        return name;
    }
}
