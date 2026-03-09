package blackjack.domain;

import java.util.List;

public abstract class Participant {

    protected final Cards drawnCards;

    protected Participant() {
        this.drawnCards = new Cards();
    }

    public void receiveOneCard(Card card) {
        drawnCards.addCard(card);
    }

    public List<String> getCardNames() {
        return drawnCards.getCardNames();
    }

    public boolean isBust() {
        return drawnCards.sumScore() > 21;
    }

    public int calculateTotalScore() {
        return drawnCards.sumScore();
    }
}
