package controller.result;

import model.judgement.PlayerResult;
import model.paticipant.Player;

public interface ResultReporter<T extends Player> {

    void report(PlayerResult<T> playerResult);
}
