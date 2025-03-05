package domain;

import java.util.List;

public abstract class Participant {

    protected Cards cards;

    public Participant(Cards cards) {
        this.cards = cards;
    }

    public Participant drawCard(List<Card> providedCards) {
        return createParticipant(providedCards);
    }

    public boolean checkExceedTwentyOne() {
        return cards.checkExceedTwentyOne();
    }

    public int getTotalNumberSum() {
        return cards.calculateTotalCardNumber();
    }

    protected abstract Participant createParticipant(List<Card> cards);
}
