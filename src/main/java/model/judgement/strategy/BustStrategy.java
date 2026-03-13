package model.judgement.strategy;

import model.judgement.JudgeStrategy;
import model.judgement.ResultStatus;
import model.paticipant.Dealer;
import model.paticipant.Player;

public class BustStrategy implements JudgeStrategy {

    @Override
    public boolean isApplicable(Dealer dealer, Player player) {
        return player.isBust() || dealer.isBust();
    }

    @Override
    public ResultStatus getResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return ResultStatus.LOSE;
        }
        return ResultStatus.WIN;
    }
}