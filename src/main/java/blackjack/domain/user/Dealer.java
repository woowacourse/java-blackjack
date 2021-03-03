package blackjack.domain.user;

public class Dealer extends User {
    private static final String NAME = "딜러";
    private static final int DRAW_LIMIT = 16;

    @Override
    public int getHitLimit() {
        return 0;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
