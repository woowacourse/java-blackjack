package blackjack.domain;

public class Dealer extends Participant {
    private static final int DEALER_STAND_POINT = 17;

    public Dealer() {
        super("딜러", new Hand());
    }

    public Dealer(Hand hand) {
        super("딜러", hand);
    }

    @Override
    public boolean canHit() {
        if (getTotalPoint() < DEALER_STAND_POINT) {
            return true;
        }
        return false;
    }

    public String getFirstCardNames() {
//        return hand.getFirstCardName();
        return "";
    }

    public boolean isOver17() {
//        return hand.isOver17();
        return false;
    }

}
