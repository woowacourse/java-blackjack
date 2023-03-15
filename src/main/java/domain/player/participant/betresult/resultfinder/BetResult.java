package domain.player.participant.betresult.resultfinder;

import domain.player.dealer.Dealer;
import domain.player.participant.Participant;
import domain.player.participant.betresult.resultstate.BetResultState;

public interface BetResult {

    Boolean canApply(Participant participant, Dealer dealer);

    BetResultState state();
}
