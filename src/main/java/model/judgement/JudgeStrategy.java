package model.judgement;

import model.paticipant.Dealer;
import model.paticipant.Participant;

public interface JudgeStrategy {

    boolean isApplicable(Dealer dealer, Participant participant);
    ResultStatus getResult(Dealer dealer, Participant participant);
}
