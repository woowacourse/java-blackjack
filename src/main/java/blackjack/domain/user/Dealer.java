package blackjack.domain.user;

public class Dealer extends User {
    private static final String NAME = "딜러";
    private static final int DRAW_LIMIT = 16;

    @Override
    public boolean isHit() {
        checkIfStay();
        return hand.getStatus() == HandStatus.HIT;
    }

    private void checkIfStay() {
        if (hand.getScore() > DRAW_LIMIT && hand.getStatus() == HandStatus.HIT) {
            hand.convertStatusToStay();
        }
    }

    @Override
    public String getName() {
        return NAME;
    }


}
