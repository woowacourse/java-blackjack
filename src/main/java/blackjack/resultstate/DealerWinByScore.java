package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;

public class DealerWinByScore implements ResultState {

    @Override
    public boolean isCapableWith(Participant participant, Dealer dealer) {
        return bothPlayersNotBust(participant, dealer) && dealer.hasHigherScoreThan(participant);
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.DEALER_WIN;
    }
}
