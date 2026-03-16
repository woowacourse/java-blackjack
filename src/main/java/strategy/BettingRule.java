package strategy;

import domain.result.GameResult;
import domain.result.RoundBetInfo;
import java.math.BigDecimal;

public interface BettingRule {
    BigDecimal calculateBetAmount(RoundBetInfo roundBetInfo, GameResult gameResult);
}
