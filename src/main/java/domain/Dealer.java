package domain;

public class Dealer {
    private Hand hand;

    public Dealer() {
        hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }
}
