package participant;

public enum GameResult {

    WIN,
    LOSE,
    DRAW,
    ;

    public static GameResult judge(Dealer dealer, Player player) {
        if ((dealer.isBust() && !player.isBust()) || (!player.isBust() && player.score() > dealer.score())
            || (player.isBlackjack() && !dealer.isBlackjack())) {
            return LOSE;
        }

        if (player.isBust() || player.isBlackjack() && dealer.isBlackjack()
                || dealer.isBlackjack() || player.score() < dealer.score()) {
            return WIN;
        }

        return DRAW;
    }
}
