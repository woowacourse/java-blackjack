package blackjack.resultstate;

import blackjack.player.Player;

public class ParticipantWinByScore implements ResultState {

    @Override
    public boolean isCapableWith(Player participant, Player dealer) {
        return bothPlayersNotBust(participant, dealer) && participant.hasHigherScoreThan(dealer);
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.PARTICIPANT_WIN;
    }
}
