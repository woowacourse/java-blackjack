package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

// TODO: 네이밍 적합하게 수정
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
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (dealer.isBust() || (!player.isBust() && player.getScore() > dealer.getScore())) {
            return GameResult.WIN;
        }
        if (player.isBust() || (!dealer.isBust() && player.getScore() < dealer.getScore())) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public int calculateProfit(final int bettingMoney) {
        return (int) (bettingMoney * this.profitRate);
    }
}
