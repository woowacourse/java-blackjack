package domain;

import java.util.List;

public abstract class Participant<T extends Participant<T>> {

    protected Cards cards;

    public Participant(Cards cards) {
        this.cards = cards;
    }

    public T drawCard(List<Card> providedCards) {
        return createParticipant(providedCards);
    }

    public boolean checkExceedTwentyOne() {
        return cards.checkExceedTwentyOne();
    }

    public int getTotalNumberSum() {
        return cards.calculateTotalCardNumber();
    }

    protected abstract T createParticipant(List<Card> cards);
}
