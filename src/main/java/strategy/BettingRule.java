package strategy;

import domain.result.GameResult;
import domain.result.RoundBetInfo;

public interface BettingRule {
    double calculateBetAmount(RoundBetInfo roundBetInfo, GameResult gameResult);
}
