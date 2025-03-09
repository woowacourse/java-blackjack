package domain;

public abstract class Gamer {

    private final String name;
    private final Hand hand = new Hand();

    protected Gamer(String name) {
        this.name = name;
    }

    public void prepareGame(CardDeck deck) {
        addFrom(deck);
        addFrom(deck);
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public int getScore() {
        return hand.calculateTotalPoint();
    }

    public void addFrom(CardDeck deck) {
        hand.add(deck.extractCard());
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return this.name;
    }

    public abstract void hit(CardDeck deck);
}

