package domain;

public enum Result {
    DEALER_WIN,
    PLAYER_WIN,
    PUSH;

    public static Result of(Dealer dealer, Player player) {
        int playerTotalScore = player.getTotalScore();
        int dealerTotalScore = dealer.getTotalScore();

        if (player.isBust() || !dealer.isBust() && dealerTotalScore > playerTotalScore) {
            return DEALER_WIN;
        }

        if (dealer.isBust() || !player.isBust() && dealerTotalScore < playerTotalScore) {
            return PLAYER_WIN;
        }

        return PUSH;
    }
}
