package domain.player.participant.betresult.resultfinder;

import domain.player.dealer.Dealer;
import domain.player.participant.Participant;
import domain.player.participant.betresult.resultstate.BetResultState;
import domain.player.participant.betresult.resultstate.BreakEvenState;

public class DealerBust implements BetResult {

    @Override
    public Boolean canApply(final Participant participant, final Dealer dealer) {
        return dealer.isBust() && participant.isNotBust();
    }

    @Override
    public BetResultState state() {
        return new BreakEvenState();
    }
}
