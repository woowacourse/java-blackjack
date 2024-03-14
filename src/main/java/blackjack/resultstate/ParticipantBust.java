package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;

public class ParticipantBust implements ResultState {

    @Override
    public boolean isCapableWith(Participant participant, Dealer dealer) {
        return participant.isBust();
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.DEALER_WIN;
    }
}
