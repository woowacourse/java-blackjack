package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.math.BigDecimal;

public enum GameResult {
    BLACKJACK(BigDecimal.valueOf(1.5)),
    WIN(BigDecimal.ONE),
    DRAW(BigDecimal.valueOf(0)),
    LOSE(BigDecimal.valueOf(-1));

    private final BigDecimal profitRate;

    GameResult(final BigDecimal profitRate) {
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

    public BigDecimal profitRate() {
        return profitRate;
    }
}
