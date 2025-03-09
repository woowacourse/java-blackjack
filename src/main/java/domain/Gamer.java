package domain;

public abstract class Gamer {

    private final String name;
    private final Deck deck = new Deck();

    protected Gamer(String name) {
        this.name = name;
    }

    public abstract boolean hit(Deck cards);

    public void prepareGame(Deck totalCard) {
        add(totalCard);
        add(totalCard);
    }

    public void add(Deck totalCards) {
        deck.add(totalCards.extractCard());
    }

    public boolean isBust() {
        return deck.isBust();
    }

    public int getScore() {
        return deck.calculateTotalPoint();
    }

    public String getName() {
        return this.name;
    }

    public Deck getDeck() {
        return deck;
    }
}

