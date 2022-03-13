package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Participant;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParticipantResult {

    private final Map<Participant, Result> participantResult;

    public ParticipantResult(final Dealer dealer, final Participants participants) {
        participantResult = new LinkedHashMap<>();
        decideResults(dealer, participants);
    }

    private void decideResults(final Dealer dealer, final Participants participants) {
        decideParticipantsResult(dealer, participants);
    }

    private void decideParticipantsResult(final Dealer dealer, final Participants participants) {
        final int dealerScore = dealer.getTotalScore();
        for (Participant participant : participants) {
            final int participantScore = participant.getTotalScore();
            participantResult.put(participant, Result.decide(dealerScore, participantScore));
        }
    }

    public Map<Result, Integer> getDealerResult() {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        for (Result result : Result.values()) {
            dealerResult.put(result, countDealerResult(result.getOpposite()));
        }
        return dealerResult;
    }

    private int countDealerResult(final Result result) {
        return (int) participantResult.values()
                .stream()
                .filter(value -> value == result)
                .count();
    }

    public Map<Participant, Result> getParticipantResult() {
        return participantResult;
    }
}
