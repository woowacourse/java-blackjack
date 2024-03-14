package blackjack.resultstate;

import blackjack.player.Player;
import java.util.List;

public class Referee {

    private final List<ResultState> states = List.of(
            new ParticipantBlackJack(),
            new DealerBlackJack(),
            new ParticipantBust(),
            new DealerBust(),
            new ParticipantWinByScore(),
            new DealerWinByScore()
    );

    public MatchResult judge(Player participant, Player dealer) {
        return states.stream()
                .filter(state -> state.isCapableWith(participant, dealer))
                .findFirst()
                .orElse(new Tie())
                .getMatchResult();
    }
}
