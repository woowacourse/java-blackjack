package blackjack.domain.participant;

public class Dealer extends Player {

    public static final int LIMIT_POINT = 16;

    public Dealer() {
        super();
    }

    @Override
    int limit() {
        return LIMIT_POINT;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isGuest() {
        return false;
    }
}
