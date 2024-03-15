package domain;

public enum PlayerGameResult {
    LOSE(-1),
    WIN(1),
    BLACKJACK_WIN(1.5f),
    PUSH(0),
    ;

    private final float winningRate;

    PlayerGameResult(float winningRate) {
        this.winningRate = winningRate;
    }

    public static PlayerGameResult of(Dealer dealer, Player player) {
        if (player.isBlackJack()) {
            return getResultWhenPlayerBlackJack(dealer);
        }
        if (player.isBust()) {
            return getResultWhenPlayerBust();
        }

        return getResultWhenPlayerNotBustAndNotBlackJack(player, dealer);
    }

    private static PlayerGameResult getResultWhenPlayerBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return PUSH;
        }

        return BLACKJACK_WIN;
    }

    private static PlayerGameResult getResultWhenPlayerBust() {
        return LOSE;
    }

    private static PlayerGameResult getResultWhenPlayerNotBustAndNotBlackJack(Player player, Dealer dealer) {
        if (dealer.isBust() || player.getTotalScore() > dealer.getTotalScore()) {
            return WIN;
        }
        if (dealer.isBlackJack() || player.getTotalScore() < dealer.getTotalScore()) {
            return LOSE;
        }

        return PUSH;
    }

    public float getWinningRate() {
        return this.winningRate;
    }
}
