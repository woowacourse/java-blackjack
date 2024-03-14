package blackjack.resultstate;

import blackjack.player.Player;

public class ParticipantBlackJack implements ResultState {

    @Override
    public boolean isCapableWith(Player participant, Player dealer) {
        return participant.isBlackJack() && dealer.isNotBlackJack();
    }

    @Override
    public MatchResult getMatchResult() {
        return MatchResult.PARTICIPANT_BLACKJACK_WIN;
    }
}
