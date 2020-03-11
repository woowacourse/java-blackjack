package domain.user;

public class Dealer extends User {
    private static final int SHOULD_ADD_CARD_POINT = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean shouldAddCard() {
        return getScore() <= SHOULD_ADD_CARD_POINT;
    }

    public boolean isWin(User that) {
        return isNotBust() && (that.isBust() || getScore() > that.getScore());
    }
}
