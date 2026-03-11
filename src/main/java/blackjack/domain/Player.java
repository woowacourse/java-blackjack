package blackjack.domain;

public class Player extends Participant {

    private static final int BLACKJACK_POINT = 21;

    public Player(String name) {
        super(name, new Hand());

    }

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean canHit() {
        if (getTotalPoint() < BLACKJACK_POINT) {
            return true;
        }
        return false;
    }

    public String getName() {
        return super.getName();
    }

}
