package domain;

import java.util.Collections;
import java.util.List;

public class Player extends Participant {

    public Player(final Status status, final DrawnCards drawnCards) {
        super(status, drawnCards);
    }

    @Override
    public List<Card> openDrawnCards() {
        return Collections.unmodifiableList(drawnCards.getCards());
    }
}
