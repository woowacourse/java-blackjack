package domain;

public abstract class Participant {
    private final Deck deck;
    private final String name;

    protected Participant(Deck participantDeck, String name) {
        this.deck = participantDeck;
        this.name = name;
    }

    public boolean isBust() {
        return deck.isBust();
    }

    public int calculateDeckSum() {
        return deck.calculateCardScoreSum();
    }

    public int addCard(Card card) {
        return this.deck.addCard(card);
    }
}