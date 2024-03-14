package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;
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

    public ResultState judge(Participant participant, Dealer dealer) {
        return states.stream()
                .filter(state -> state.isCapableWith(participant, dealer))
                .findFirst()
                .orElse(new Tie());
    }
}
