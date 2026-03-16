package strategy;

import domain.result.GameResult;
import domain.result.RoundBetInfo;
import java.math.BigDecimal;
import java.util.Map;

public class DefaultBettingRule implements BettingRule {
    private static final BigDecimal BLACKJACK_MULTIPLIER = new BigDecimal("1.5");
    private static final Map<GameResult, BigDecimal> MULTIPLIERS = Map.of(
            GameResult.WIN, new BigDecimal("1"),
            GameResult.DRAW, new BigDecimal("0"),
            GameResult.LOSE, new BigDecimal("-1")
    );

    @Override
    public BigDecimal calculateBetAmount(RoundBetInfo roundBetInfo, GameResult gameResult) {
        BigDecimal betAmount = roundBetInfo.betAmount();
        if (roundBetInfo.user().isBlackjack()) {
            return betAmount.multiply(BLACKJACK_MULTIPLIER);
        }
        BigDecimal multiplier = MULTIPLIERS.get(gameResult);
        if (multiplier == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 게임 결과입니다.");
        }
        return betAmount.multiply(multiplier);
    }
}
