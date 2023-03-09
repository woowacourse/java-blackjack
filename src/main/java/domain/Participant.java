package domain;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BUST_NUMBER = 21;

    protected final Status status;
    protected final DrawnCards drawnCards;

    protected Participant(final Status status, final DrawnCards drawnCards) {
        this.status = status;
        this.drawnCards = drawnCards;
    }

    public abstract List<Card> openDrawnCards();

    public abstract boolean canDrawMore();

    public void pickCard(final Card card) {
        drawnCards.add(card);
    }

    public int calculateCardScore() {
        return drawnCards.calculateScore();
    }

    public boolean isBust() {
        return drawnCards.calculateScore() > BUST_NUMBER;
    }

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
