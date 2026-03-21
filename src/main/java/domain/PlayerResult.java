package domain;

public enum PlayerResult {
    BLACKJACK(1.5), WIN(1), TIE(0), LOSS(-1);

    private final double returnRate;

    PlayerResult(double returnRate) {
        this.returnRate = returnRate;
    }

    public static PlayerResult determinePlayerResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return PlayerResult.LOSS;
        }
        if (player.isBlackJack()) {
            if (dealer.isBlackJack()) {
                return PlayerResult.TIE;
            }
            return PlayerResult.BLACKJACK;
        }
        if (dealer.isBlackJack()) {
            return PlayerResult.LOSS;
        }
        if (dealer.isBust()) {
            return PlayerResult.WIN;
        }
        return comparePlayerScoreWithDealerScore(dealer.getCardsSum(), player.getCardsSum());
    }

    private static PlayerResult comparePlayerScoreWithDealerScore(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return PlayerResult.LOSS;
        }
        if (dealerScore == playerScore) {
            return PlayerResult.TIE;
        }
        return PlayerResult.WIN;
    }

    public double getReturnRate() {
        return returnRate;
    }
}
