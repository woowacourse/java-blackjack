package domain.player.participant.betresult.resultfinder;

import domain.player.dealer.Dealer;
import domain.player.participant.Participant;
import domain.player.participant.betresult.resultstate.BetResultState;
import domain.player.participant.betresult.resultstate.WinState;

public class ParticipantBlackjack implements BetResult {

    @Override
    public Boolean canApply(final Participant participant, final Dealer dealer) {
        return participant.isBlackjack() && !dealer.isBlackjack();
    }

    @Override
    public BetResultState state() {
        return new WinState();
    }
}
