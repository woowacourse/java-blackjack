package domain;

import java.util.List;

public abstract class Participant<T extends Participant<T>> {

    protected Name name;
    protected Cards cards;

    public Participant(String name, Cards cards) {
        this.name = new Name(name);
        this.cards = cards;
    }

    public T drawCard(List<Card> providedCards) {
        return createParticipant(providedCards);
    }

    public boolean checkExceedBurst() {
        return cards.checkBurst();
    }

    public int getTotalNumberSum() {
        return cards.calculateTotalCardNumber();
    }

    protected abstract T createParticipant(List<Card> cards);

    public int calculateDifferenceFromTwentyOne() {
        return cards.calculateDifferenceFromBurst();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }
}
