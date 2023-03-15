package domain.player.participant.betresult.resultfinder;

import domain.player.dealer.Dealer;
import domain.player.participant.Participant;
import domain.player.participant.betresult.resultstate.BetResultState;
import domain.player.participant.betresult.resultstate.BreakEvenState;

import java.util.List;

public class BetResultFinder {

    private final List<BetResult> betResults
            = List.of(
            new DealerBlackjack(),
            new DealerBust(),
            new ParticipantBlackjack(),
            new ParticipantBust(),
            new ParticipantLose(),
            new PlayerDraw()
    );

    public BetResultState findStateOf(Participant participant, Dealer dealer) {
        return betResults.stream()
                         .filter(it -> it.canApply(participant, dealer))
                         .map(BetResult::state)
                         .findFirst()
                         .orElse(new BreakEvenState());
    }
}
