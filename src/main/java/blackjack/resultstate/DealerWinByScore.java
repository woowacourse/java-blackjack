package blackjack.resultstate;

import blackjack.player.Player;

public class DealerWinByScore implements ResultState {

    @Override
    public boolean isCapableWith(Player participant, Player dealer) {
        return bothPlayersNotBust(participant, dealer) && dealer.hasHigherScoreThan(participant);
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.DEALER_WIN;
    }
}
