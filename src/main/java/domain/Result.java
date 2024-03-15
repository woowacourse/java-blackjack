package domain;

public enum Result {
    DEALER_WIN(-1),
    PLAYER_WIN(1),
    PLAYER_BLACK_JACK_WIN(1.5f),
    PUSH(0),
    ;

    private final float playerWinningRate;

    Result(float playerWinningRate) {
        this.playerWinningRate = playerWinningRate;
    }

    public static Result of(Dealer dealer, Player player) {
        if (player.isBlackJack()) {
            return getResultWhenPlayerBlackJack(dealer);
        }
        if (player.isBust()) {
            return getResultWhenPlayerBust();
        }

        return getResultWhenPlayerNotBustAndNotBlackJack(player, dealer);
    }

    private static Result getResultWhenPlayerBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return PUSH;
        }

        return PLAYER_BLACK_JACK_WIN;
    }

    private static Result getResultWhenPlayerBust() {
        return DEALER_WIN;
    }

    private static Result getResultWhenPlayerNotBustAndNotBlackJack(Player player, Dealer dealer) {
        if (dealer.isBust() || player.getTotalScore() > dealer.getTotalScore()) {
            return PLAYER_WIN;
        }
        if (dealer.isBlackJack() || player.getTotalScore() < dealer.getTotalScore()) {
            return DEALER_WIN;
        }

        return PUSH;
    }

    public float getPlayerWinningRate() {
        return playerWinningRate;
    }
}
