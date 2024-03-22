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

    // TODO: 테스트 작성
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

    public double profitRate() {
        return profitRate;
    }
}
