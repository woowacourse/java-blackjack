package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public enum GameResult {
    BLACKJACK(1.5f),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final float profitRate;

    GameResult(final float profitRate) {
        this.profitRate = profitRate;
    }

    public static GameResult ofPlayer(final Player player, final Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        return calculateResult(player.getScore(), dealer.getScore());
    }

    private static GameResult calculateResult(final int playerScore, final int dealerScore) {
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public int calculateProfit(final int bettingMoney) {
        return (int) (bettingMoney * this.profitRate);
    }
}
