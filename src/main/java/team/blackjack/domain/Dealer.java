package team.blackjack.domain;

public class Dealer {

    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Card draw(Deck deck) {
        return deck.draw();
    }

    public Hand getHand() {
        return this.hand;
    }
}