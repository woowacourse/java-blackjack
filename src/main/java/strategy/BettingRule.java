package strategy;

import domain.GameResult;
import domain.RoundBetInfo;

public interface BettingRule {
    int calculateBetAmount(RoundBetInfo roundBetInfo, GameResult gameResult);
}
