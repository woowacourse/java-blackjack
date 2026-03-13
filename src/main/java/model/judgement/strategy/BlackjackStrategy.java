package model.judgement.strategy;

import model.judgement.JudgeStrategy;
import model.judgement.ResultStatus;
import model.paticipant.Dealer;
import model.paticipant.Player;

public class BlackjackStrategy implements JudgeStrategy {

    @Override
    public boolean isApplicable(Dealer dealer, Player player) {
        return dealer.isBlackjack() || player.isBlackjack();
    }

    @Override
    public ResultStatus getResult(Dealer dealer, Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return ResultStatus.DRAW;
        }
        if (player.isBlackjack()) {
            return ResultStatus.BLACKJACK;
        }
        return ResultStatus.LOSE;
    }
}