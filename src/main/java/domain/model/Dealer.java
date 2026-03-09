package domain.model;

public class Dealer implements Person {

    private Deck deck;
    private int finalSum = 0;

    private Dealer(Deck deck) {
        this.deck = deck;
    }

    public static Dealer of(Deck deck) {
        return new Dealer(deck);
    }

    public Deck getDeck() {
        return deck;
    }

    public int getFinalSum() {
        return finalSum;
    }

    public void calculateFinalSum() {
        finalSum = deck.calculateFinalSum();
    }

    public boolean isBurst() {
        return deck.getDeckStatus() == DeckStatus.BURST;
    }

    public boolean isAlive() {
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
