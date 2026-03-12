package strategy;

import domain.result.GameResult;
import domain.result.RoundBetInfo;

public class DefaultBettingRule implements BettingRule {
    private static final double BLACKJACK_MULTIPLIER = 1.5;
    private static final double WIN_MULTIPLIER = 1;
    private static final double DRAW_MULTIPLIER = 0;
    private static final double LOSE_MULTIPLIER = -1;

    @Override
    public int calculateBetAmount(RoundBetInfo roundBetInfo, GameResult gameResult) {
        int betAmount = roundBetInfo.betAmount();
        boolean isBlackjack = roundBetInfo.user().isBlackjack();
        if (isBlackjack) {
            return (int) (betAmount * BLACKJACK_MULTIPLIER);
        }
        if (gameResult == GameResult.WIN) {
            return (int) (betAmount * WIN_MULTIPLIER);
        }
        if (gameResult == GameResult.DRAW) {
            return (int) (betAmount * DRAW_MULTIPLIER);
        }
        if (gameResult == GameResult.LOSE) {
            return (int) (betAmount * LOSE_MULTIPLIER);
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 게임 결과입니다.");
    }
}
