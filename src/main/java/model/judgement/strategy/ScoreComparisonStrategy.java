package model.judgement.strategy;

import model.judgement.JudgeStrategy;
import model.judgement.ResultStatus;
import model.paticipant.Dealer;
import model.paticipant.Player;

public class ScoreComparisonStrategy implements JudgeStrategy {

    @Override
    public boolean isApplicable(Dealer dealer, Player player) {
        return true; 
    }

    @Override
    public ResultStatus getResult(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();
        return determineResult(playerScore, dealerScore);
    }

    private ResultStatus determineResult(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return ResultStatus.WIN;
        }
        if (playerScore == dealerScore) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }
}