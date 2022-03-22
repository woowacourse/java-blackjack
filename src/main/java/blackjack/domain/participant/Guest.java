package blackjack.domain.participant;

public class Guest extends Player {

    public static final int LIMIT_POINT = 21;

    public Guest(String name) {
        super(name);
    }

    @Override
    int limit() {
        return LIMIT_POINT;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean isGuest() {
        return true;
    }
}
