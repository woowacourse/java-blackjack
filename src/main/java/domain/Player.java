package domain;

import java.util.Collections;
import java.util.List;

public class Player extends Participant {

    protected Player(final Name name, final DrawnCards drawnCards) {
        super(name, drawnCards);
    }

    @Override
    public List<Card> openDrawnCards() {
        return Collections.unmodifiableList(drawnCards.getCards());
    }
}
