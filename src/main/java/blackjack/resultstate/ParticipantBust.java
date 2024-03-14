package blackjack.resultstate;

import blackjack.player.Player;

public class ParticipantBust implements ResultState {

    @Override
    public boolean isCapableWith(Player participant, Player dealer) {
        return participant.isBust();
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.DEALER_WIN;
    }
}
