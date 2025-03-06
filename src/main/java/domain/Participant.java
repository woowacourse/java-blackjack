package domain;

import java.util.List;

public abstract class Participant<T extends Participant<T>> {

    protected Name name;
    protected Cards cards;

    public Participant(Name name, Cards cards) {
        this.name = name;
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
