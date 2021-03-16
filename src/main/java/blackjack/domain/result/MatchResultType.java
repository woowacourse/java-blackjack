package blackjack.domain.result;

import static blackjack.domain.card.Cards.BLACKJACK_SCORE;

public enum MatchResultType {
    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double earningRate;

    MatchResultType(double earningRate) {
        this.earningRate = earningRate;
    }

    public static MatchResultType getMatchResultType(int dealerScore, int playerScore) {
        if (isDealerBusted(dealerScore) || (playerScore > dealerScore && isPlayerNotBusted(playerScore))) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    private static boolean isDealerBusted(int dealerScore) {
        return dealerScore > BLACKJACK_SCORE;
    }

    private static boolean isPlayerNotBusted(int playerScore) {
        return playerScore <= BLACKJACK_SCORE;
    }

    public double calculateProfit(int bettingMoney) {
        return this.earningRate * bettingMoney;
    }
}
