package model.judgement.strategy;

import model.judgement.JudgeStrategy;
import model.judgement.ResultStatus;
import model.paticipant.Dealer;
import model.paticipant.Participant;

public class BustStrategy implements JudgeStrategy {

    @Override
    public boolean isApplicable(Dealer dealer, Participant participant) {
        return participant.isBust() || dealer.isBust();
    }

    @Override
    public ResultStatus getResult(Dealer dealer, Participant participant) {
        if (participant.isBust()) {
            return ResultStatus.LOSE;
        }
        return ResultStatus.WIN;
    }
}