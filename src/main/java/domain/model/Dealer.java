package domain.model;

public class Dealer implements Person {

    private Deck deck;

    private Dealer(Deck deck) {
        this.deck = deck;
    }

    public static Dealer of(Deck deck) {
        return new Dealer(deck);
    }

    public void calculateFinalSum() {
        deck.calculateFinalSum();
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isBurst() {
        return deck.getDeckStatus() == DeckStatus.BURST;
    }

    public boolean isAlice() {
        return deck.getDeckStatus() == DeckStatus.ALIVE;
    }

    @Override
    public int getDeckSum() {
        return deck.getSum();
    }

    @Override
    public int getDeckSize() {
        return deck.getSize();
    }

    @Override
    public void appendCard(Card card) {
        deck.append(card);
    }
}
