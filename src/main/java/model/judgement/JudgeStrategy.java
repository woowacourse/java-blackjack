package model.judgement;

import model.paticipant.Dealer;
import model.paticipant.Player;

public interface JudgeStrategy {

    boolean isApplicable(Dealer dealer, Player player);
    ResultStatus getResult(Dealer dealer, Player player);
}
