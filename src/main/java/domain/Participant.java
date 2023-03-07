package domain;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final Status status;
    protected final DrawnCards drawnCards;

    protected Participant(final Status status, final DrawnCards drawnCards) {
        this.status = status;
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
        return status.getName();
    }

    public List<Card> getDrawnCards() {
        return Collections.unmodifiableList(drawnCards.getCards());
    }

    public int getAccount() {
        return status.getAccount();
    }
}
