package strategy;

import domain.GameResult;

public interface BettingRule {
    int calculateBetAmount(int betAmount, GameResult gameResult, boolean isBlackjack);
}
