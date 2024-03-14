package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;

public class DealerBust implements ResultState {

    @Override
    public boolean isCapableWith(Participant participant, Dealer dealer) {
        return participant.isNotBust() && dealer.isBust();
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.PARTICIPANT_WIN;
    }
}
