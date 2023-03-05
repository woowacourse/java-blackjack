package blackjack.domain;

public enum GameResult {
    WIN,
    LOSE;

    public static GameResult of(Player player, Dealer dealer) {
        if (player.getState().isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.getState().isBust()) {
            return GameResult.WIN;
        }
        if (player.getScore() >= dealer.getScore()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    public boolean isWin() {
        return this == WIN;
    }
}
