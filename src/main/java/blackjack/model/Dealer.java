package blackjack.model;

public class Dealer {
    private static final int ACTION_CONDITION = 17;

    private final Hand hand;

    public Dealer(final NumberGenerator numberGenerator) {
        this.hand = new Hand(numberGenerator);
    }

    public void doAction(final NumberGenerator numberGenerator) {
        while (hand.calculateCardsTotal() < ACTION_CONDITION) {
            hand.addCard(numberGenerator);
        }
    }

    public Hand getHand() {
        return hand;
    }
}
