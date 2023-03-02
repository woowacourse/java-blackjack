package model;

public class Dealer extends User {

    private static final int CAN_RECEIVE_DEALER_MAX_NUMBER = 16;

    private static final Dealer DEALER = new Dealer("딜러", Hand.create());

    public Dealer(final String name, final Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean canReceiveCard() {
        return CAN_RECEIVE_DEALER_MAX_NUMBER >= calculateTotalValue();
    }

    public static Dealer getInstance() {
        return Dealer.DEALER;
    }

    public String getName() {
        return super.getName();
    }
}
