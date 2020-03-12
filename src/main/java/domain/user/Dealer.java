package domain.user;

public class Dealer extends Player {
    private static final int SHOULD_ADD_CARD_POINT = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean shouldAddCard() {
        return getScore() <= SHOULD_ADD_CARD_POINT;
    }

    public boolean isWin(Player opponentPlayer) {
        return isNotBust() && (opponentPlayer.isBust() || getScore() > opponentPlayer.getScore());
    }
}
