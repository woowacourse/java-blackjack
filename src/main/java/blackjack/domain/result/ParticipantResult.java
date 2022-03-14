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
        Map<Participant, Result> result = new LinkedHashMap<>();
        for (Participant participant : participants) {
            result.put(participant, Result.of(dealer, participant));
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
