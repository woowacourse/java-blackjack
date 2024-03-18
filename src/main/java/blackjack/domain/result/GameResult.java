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

    public static GameResult getGameResult(final Player player, final Dealer dealer) {
        if (player.isBlackjack()) {
            if (dealer.isBlackjack()) {
                return GameResult.DRAW;
            }
            return GameResult.BLACKJACK;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (player.getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return GameResult.DRAW;
        }
        if (player.getScore() < dealer.getScore()) {
            return GameResult.LOSE;
        }
        return GameResult.LOSE;
    }

    public int calculateProfit(final int bettingMoney) {
        return (int) (bettingMoney * this.profitRate);
    }
}
