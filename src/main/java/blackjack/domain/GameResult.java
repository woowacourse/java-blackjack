package blackjack.domain;

public enum GameResult {
    WIN,
    LOSE,
    DRAW;

    public static GameResult getGameResult(Player dealer, Player player) {
        if (dealer.compareTo(player) > 0) {
            return GameResult.LOSE;
        }
        if (dealer.compareTo(player) < 0) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}

