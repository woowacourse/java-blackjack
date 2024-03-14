package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;

public class ParticipantBlackJack implements ResultState {

    @Override
    public boolean isCapableWith(Participant participant, Dealer dealer) {
        return participant.isBlackJack() && dealer.isNotBlackJack();
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.PARTICIPANT_BLACKJACK_WIN;
    }
}
