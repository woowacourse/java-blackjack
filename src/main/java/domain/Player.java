package domain;

import java.util.List;

public class Player extends Participant {

    public Player(final Name name, final List<Card> cards) {
        super(name, cards);
    }
}
