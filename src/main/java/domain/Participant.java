package domain;

public abstract class Participant {
    protected final Cards cards;

    protected Participant(Cards cards) {
        this.cards = cards;
    }

    public Cards getCards() {
        return cards;
    }

    public void addCards(Cards receivedCards) {
        cards.addAll(receivedCards);
    }

    public int calculateCardsSum() {
        return cards.calculateSumResult();
    }
}
