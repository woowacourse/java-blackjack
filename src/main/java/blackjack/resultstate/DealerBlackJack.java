package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;

public class DealerBlackJack implements ResultState {

    @Override
    public boolean isCapableWith(Participant participant, Dealer dealer) {
        return participant.isNotBlackJack() && dealer.isBlackJack();
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.DEALER_WIN;
    }
}
