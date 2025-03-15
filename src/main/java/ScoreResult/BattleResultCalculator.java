package ScoreResult;

import game.GameRule;

public class BattleResultCalculator {

    public BattleResult calculate(int dealerScore, int playerScore) {
        if (hasBust(dealerScore, playerScore)) {
            return determineBustResult(dealerScore, playerScore);
        }
        return determineNormalResult(dealerScore, playerScore);
    }

    private boolean hasBust(int dealerScore, int playerScore) {
        return GameRule.checkBust(dealerScore) || GameRule.checkBust(playerScore);
    }

    private BattleResult determineBustResult(int dealerScore, int playerScore) {
        if ((GameRule.checkBust(playerScore) && GameRule.checkBust(dealerScore)) || GameRule.checkBust(playerScore)) {
            return BattleResult.LOSE;
        }
        return BattleResult.WIN;
    }

    private BattleResult determineNormalResult(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) {
            return BattleResult.WIN;
        }
        if (playerScore < dealerScore) {
            return BattleResult.LOSE;
        }
        return BattleResult.DRAW;
    }
}

