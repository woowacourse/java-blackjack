package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParticipantResult {

    private final Map<Participant, Integer> yields;

    public ParticipantResult(final Dealer dealer, final Participants participants) {
        yields = getYields(dealer, participants);
    }

    private Map<Participant, Integer> getYields(final Dealer dealer, final Participants participants) {
        Map<Participant, Integer> yields = new LinkedHashMap<>();

        for (Participant participant : participants) {
            Result result = Result.decide(dealer, participant);
            yields.put(participant, participants.getRevenue(participant, result));
        }
        return yields;
    }

    public int getDealerYield() {
        return (-1) * yields.values().stream()
                .mapToInt(yield -> yield)
                .sum();
    }

    public Map<Participant, Integer> getParticipantYields() {
        return yields;
    }
}
