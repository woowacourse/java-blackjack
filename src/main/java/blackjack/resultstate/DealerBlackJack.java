package blackjack.resultstate;

import blackjack.player.Player;

public class DealerBlackJack implements ResultState {

    @Override
    public boolean isCapableWith(Player participant, Player dealer) {
        return participant.isNotBlackJack() && dealer.isBlackJack();
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.DEALER_WIN;
    }
}
