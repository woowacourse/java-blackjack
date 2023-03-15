package domain.player.participant.betresult.resultfinder;

import domain.player.dealer.Dealer;
import domain.player.participant.Participant;
import domain.player.participant.betresult.resultstate.BetResultState;
import domain.player.participant.betresult.resultstate.LoseState;

public class ParticipantBust implements BetResult {

    @Override
    public Boolean canApply(final Participant participant, final Dealer dealer) {
        return participant.isBust();
    }

    @Override
    public BetResultState state() {
        return new LoseState();
    }
}
