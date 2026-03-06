package domain;

public class Dealer {

    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void hit(Card card) {
        hand.drawCard(card);
    }

    public Card getOpenCard() {
        return hand.getHand().getFirst();
    }

    public Hand getHand() {
        return hand;
    }

    public boolean shouldHit() {
        return hand.calculateSum() <= 16;
    }
}
