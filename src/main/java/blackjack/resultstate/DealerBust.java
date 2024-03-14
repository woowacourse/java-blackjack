package blackjack.resultstate;

import blackjack.player.Player;

public class DealerBust implements ResultState {

    @Override
    public boolean isCapableWith(Player participant, Player dealer) {
        return participant.isNotBust() && dealer.isBust();
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.PARTICIPANT_WIN;
    }
}
