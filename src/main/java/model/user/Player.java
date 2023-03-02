package model.user;

public class Player extends User {

    private static final int CAN_RECEIVE_MAX_NUMBER = 21;

    public Player(final String name, final Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean canReceiveCard() {
        return CAN_RECEIVE_MAX_NUMBER >= calculateTotalValue();
    }

    public String getName() {
        return super.getName();
    }

}
