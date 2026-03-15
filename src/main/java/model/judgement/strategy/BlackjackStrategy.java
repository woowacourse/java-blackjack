package model.judgement.strategy;

import model.judgement.JudgeStrategy;
import model.judgement.ResultStatus;
import model.paticipant.Dealer;
import model.paticipant.Participant;

public class BlackjackStrategy implements JudgeStrategy {

    @Override
    public boolean isApplicable(Dealer dealer, Participant participant) {
        return dealer.isBlackjack() || participant.isBlackjack();
    }

    @Override
    public ResultStatus getResult(Dealer dealer, Participant participant) {
        if (dealer.isBlackjack() && participant.isBlackjack()) {
            return ResultStatus.DRAW;
        }
        if (participant.isBlackjack()) {
            return ResultStatus.BLACKJACK;
        }
        return ResultStatus.LOSE;
    }
}