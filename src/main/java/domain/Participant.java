package domain;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final Name name;
    protected final DrawnCards drawnCards;

    protected Participant(final Name name, final DrawnCards drawnCards) {
        this.name = name;
        this.drawnCards = drawnCards;
    }

    public void pickCard(final Card card) {
        drawnCards.add(card);
    }

    public int calculateCardScore() {
        return drawnCards.calculateScore();
    }

    public abstract List<Card> openDrawnCards();

    public String getName() {
        return name.getName();
    }

    public List<Card> getDrawnCards() {
        return Collections.unmodifiableList(drawnCards.getCards());
    }
}
