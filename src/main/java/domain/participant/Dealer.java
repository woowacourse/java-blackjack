package domain.participant;

public class Dealer extends Participant {
    private static final int MAX_DEALER_HAND_VALUE = 16;

    Dealer(final Hand hand) {
        super(hand);
    }

    public static Dealer init() {
        return new Dealer(Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.getCardsNumberSum() <= MAX_DEALER_HAND_VALUE;
    }

    public boolean isDealerWin(final Player player) {
        if (player.isNotBurst()) {
            return hand.isNotBurst() && (hand.getCardsNumberSum() > player.getHandSum());
        }
        return true;
    }
}
