package domain.participant;

import domain.card.Card;
import domain.card.DrawnCards;
import java.util.Collections;
import java.util.List;

public class Player extends Participant {

    private static final int BURST_NUMBER = 21;

    public Player(final Name name, final DrawnCards drawnCards) {
        super(name, drawnCards);
    }

    @Override
    public List<Card> openDrawnCards() {
        return Collections.unmodifiableList(drawnCards.getCards());
    }

    @Override
    public boolean isDrawable() {
        return calculateCardScore() < BURST_NUMBER;
    }
}
