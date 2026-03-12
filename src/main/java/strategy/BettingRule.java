package strategy;

import domain.result.GameResult;
import domain.result.RoundBetInfo;

public interface BettingRule {
    int calculateBetAmount(RoundBetInfo roundBetInfo, GameResult gameResult);
}
