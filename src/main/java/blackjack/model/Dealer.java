package blackjack.model;

public class Dealer {

    private final Hand hand;
    private final DealerDrawPolicy drawPolicy;

    public Dealer(Hand hand, DealerDrawPolicy drawPolicy) {
        this.hand = hand;
        this.drawPolicy = drawPolicy;
    }

    public boolean shouldDraw() {
        return drawPolicy.shouldDraw(hand.calculateScore());
    }
}
