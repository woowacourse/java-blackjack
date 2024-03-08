package domain;

public enum Result {
    DEALER_WIN,
    PLAYER_WIN,
    PUSH;

    public static Result of(Dealer dealer, Player player) { //TODO: 시간 남으면 생각해보기
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
