package domain;

public enum WinningCondition {
    WIN(1), DRAW(0), LOSE(-1), BLACK_JACK(1.5);

    private final double rate;

    WinningCondition(double rate) {
        this.rate = rate;
    }

    public static WinningCondition from(Participant player, Participant dealer) {
        if (isBlackJackCase(player, dealer)) {
            return blackJackResult(player, dealer);
        }

        if (isBustCase(player, dealer)) {
            return bustResult(player);
        }

        return compareResult(player, dealer);
    }

    public double earning(int betAmount) {
        return rate * betAmount;
    }

    private static boolean isBlackJackCase(Participant player, Participant dealer) {
        return player.isBlackJack() || dealer.isBlackJack();
    }

    private static WinningCondition blackJackResult(Participant player, Participant dealer) {
        if (isBothBlackJack(player, dealer)) {
            return DRAW;
        }
        return BLACK_JACK;
    }

    private static boolean isBothBlackJack(Participant player, Participant dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean isBustCase(Participant player, Participant dealer) {
        return isPlayerBust(player) || isDealerBust(dealer);
    }

    private static WinningCondition bustResult(Participant player) {
        if (isPlayerBust(player)) {
            return LOSE;
        }

        return WIN;
    }

    private static boolean isPlayerBust(Participant player) {
        return player.isBusted();
    }

    private static WinningCondition compareResult(Participant player, Participant dealer) {
        if (isDraw(player, dealer)) {
            return DRAW;
        }

        if (isLose(player, dealer)) {
            return LOSE;
        }

        return WIN;
    }

    private static boolean isDealerBust(Participant dealer) {
        return dealer.isBusted();
    }

    private static boolean isDraw(Participant player, Participant dealer) {
        return player.score() == dealer.score();
    }

    private static boolean isLose(Participant player, Participant dealer) {
        return player.score() < dealer.score();
    }
}
