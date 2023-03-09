package domain;

import java.util.List;

public class Player extends Participant {

    public Player(final Name name, final Cards cards) {
        super(name, cards);
    }

    @Override
    public List<String> printInitCards() {
        return cards.cardsToString();
    }
}
