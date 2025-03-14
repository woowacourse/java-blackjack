package participant;

public enum GameResult {

    WIN,
    LOSE,
    DRAW,
    ;

    public static GameResult judge(Player player, Dealer dealer) {
        if (!player.isBust() && (player.score() > dealer.score() || dealer.isBust())) {
            return WIN;
        }
        if (!dealer.isBust() && (player.score() < dealer.score() || player.isBust())) {
            return LOSE;
        }
        return DRAW;
    }
}
