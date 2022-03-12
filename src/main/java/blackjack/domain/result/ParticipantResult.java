package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParticipantResult {

    private final Map<Participant, Result> result;

    public ParticipantResult(final Dealer dealer, final Participants participants) {
        this.result = decideResult(dealer, participants);
    }

    private Map<Participant, Result> decideResult(final Dealer dealer, final Participants participants) {
        final int dealerScore = dealer.getTotalScore();

        Map<Participant, Result> result = new LinkedHashMap<>();
        for (Participant participant : participants) {
            final int participantScore = participant.getTotalScore();
            result.put(participant, Result.decide(dealerScore, participantScore));
        }
        return result;
    }

    public int countResult(final Result result) {
        return (int) this.result.values()
                .stream()
                .filter(value -> value == result)
                .count();
    }

    public Map<Participant, Result> getResult() {
        return result;
    }
}
