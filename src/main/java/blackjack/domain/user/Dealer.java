package blackjack.domain.user;

public class Dealer extends User {
    private static final String NAME = "딜러";
    private static final int DRAW_LIMIT = 16;

    private void checkIfStay() {
        if (hand.isGreaterScoreThan(DRAW_LIMIT) && hand.isSameStatus(HandStatus.HIT)) {
            hand.convertStatusToStay();
        }
    }

    @Override
    public boolean isHit() {
        checkIfStay();
        return hand.isSameStatus(HandStatus.HIT);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
