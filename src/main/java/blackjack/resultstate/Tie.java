package blackjack.resultstate;

import blackjack.player.Player;

public class Tie implements ResultState {

    @Override
    public boolean isCapableWith(Player participant, Player dealer) {
        return bothPlayersNotBust(participant, dealer) && participant.hasSameScoreWith(dealer);
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.TIE;
    }
}
