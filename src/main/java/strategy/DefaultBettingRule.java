package strategy;

import domain.result.GameResult;
import domain.result.RoundBetInfo;
import java.util.Map;

public class DefaultBettingRule implements BettingRule {
    private static final double BLACKJACK_MULTIPLIER = 1.5;
    private static final Map<GameResult, Double> MULTIPLIERS = Map.of(
            GameResult.WIN, 1.0,
            GameResult.DRAW, 0.0,
            GameResult.LOSE, -1.0
    );

    @Override
    public double calculateBetAmount(RoundBetInfo roundBetInfo, GameResult gameResult) {
        double betAmount = roundBetInfo.betAmount();
        if (roundBetInfo.user().isBlackjack()) {
            return betAmount * BLACKJACK_MULTIPLIER;
        }
        Double multiplier = MULTIPLIERS.get(gameResult);
        if (multiplier == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 게임 결과입니다.");
        }
        return betAmount * multiplier;
    }
}
