package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Participant;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParticipantResult {

    private final Map<Participant, Result> results;

    public ParticipantResult(final Dealer dealer, final Participants participants) {
        results = getResults(dealer, participants);
    }

    private Map<Participant, Result> getResults(final Dealer dealer, final Participants participants) {
        Map<Participant, Result> results = new LinkedHashMap<>();
        final int dealerScore = dealer.getTotalScore();
        for (Participant participant : participants) {
            final int participantScore = participant.getTotalScore();
            results.put(participant, Result.decide(dealerScore, participantScore));
        }
        return results;
    }

    public Map<Result, Integer> getDealerResult() {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        for (Result result : Result.values()) {
            dealerResult.put(result, countDealerResult(result.getOpposite()));
        }
        return dealerResult;
    }

    private int countDealerResult(final Result result) {
        return (int) results.values()
                .stream()
                .filter(value -> value == result)
                .count();
    }

    public Map<Participant, Result> getParticipantResult() {
        return results;
    }
}
