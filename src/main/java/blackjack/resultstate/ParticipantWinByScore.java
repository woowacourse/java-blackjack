package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;

public class ParticipantWinByScore implements ResultState {

    @Override
    public boolean isCapableWith(Participant participant, Dealer dealer) {
        return bothPlayersNotBust(participant, dealer) && participant.hasHigherScoreThan(dealer);
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.PARTICIPANT_WIN;
    }
}
