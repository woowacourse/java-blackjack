package domain;

public abstract class Gamer {

    protected final String name;
    protected final Hand hand = new Hand();

    protected Gamer(String name) {
        this.name = name;
    }

    public abstract void hit(Deck cards);

    public void prepareGame(Deck deck) {
        add(deck);
        add(deck);
    }

    public void add(Deck deck) {
        hand.hit(deck.extractCard());
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int getScore() {
        return hand.calculateTotalScore();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}


