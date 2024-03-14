package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;

public class Tie implements ResultState {

    @Override
    public boolean isCapableWith(Participant participant, Dealer dealer) {
        return bothPlayersNotBust(participant, dealer) && participant.hasSameScoreWith(dealer);
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.TIE;
    }
}
