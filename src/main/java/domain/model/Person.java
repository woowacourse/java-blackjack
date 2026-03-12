package domain.model;

public abstract class Person {

    private Deck deck;
    private double profit = 0.0;

    public Person(Deck deck) {
        this.deck = deck;
    }

    public Person() {
    }

    public Deck getDeck() {
        return deck;
    }

    public int getFinalDeckSum() {
        return deck.calculateFinalSum();
    }

    public int getDeckSize() {
        return deck.getSize();
    }

    public double getProfit() {
        return profit;
    }

    public void assignDeck(Deck deck) {
        this.deck = deck;
    }

    public void appendCard(Card card) {
        deck.append(card);
    }

    public boolean isBurst() {
        return deck.isBurst();
    }

    public boolean isAlive() {
        return deck.isAlive();
    }

    public boolean isBlackJack() {
        return deck.isBlackJack();
    }

    protected void plusProfit(double value) {
        profit += value;
    }

    protected void minusProfit(double value) {
        profit -= value;
    }
}
