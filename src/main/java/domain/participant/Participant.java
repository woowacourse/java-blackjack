package domain.participant;

import domain.card.Card;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

    private static final int BURST_NUMBER = 21;

    protected final Name name;
    protected final DrawnCards drawnCards;

    public Participant(final Name name, final DrawnCards drawnCards) {
        this.name = name;
        this.drawnCards = drawnCards;
    }

    public final void drawCard(final Card card) {
        drawnCards.add(card);
    }

    public final int calculateScore() {
        return drawnCards.calculateScore();
    }

    public abstract List<Card> openDrawnCards();

    public abstract boolean isDrawable();

    protected final boolean isBlackJack() {
        return drawnCards.isBlackJack();
    }

    protected final boolean isBurst() {
        return calculateScore() > BURST_NUMBER;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getDrawnCards() {
        return new ArrayList<>(drawnCards.getCards());
    }

}
