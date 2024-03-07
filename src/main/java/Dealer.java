public class Dealer extends Gamer {

    private static final String DEALER_NAME = "딜러";

    public Dealer(DealerCards cards) {
        super(DEALER_NAME, cards);
    }

    public boolean canHit() {
        DealerCards dealerCards = (DealerCards) cards;
        return dealerCards.hasScoreUnderHitThreshold();
    }
}
