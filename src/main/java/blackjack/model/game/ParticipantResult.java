package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum ParticipantResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String description;

    ParticipantResult(final String description) {
        this.description = description;
    }

    public static ParticipantResult of(final Dealer dealer, final Participant participant) {
        if (participant.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return compareDealerAndParticipantPoint(dealer, participant);
    }

    private static ParticipantResult compareDealerAndParticipantPoint(final Dealer dealer, final Participant participant) {
        int dealerPoint = dealer.calculatePoint();
        int participantPoint = participant.calculatePoint();
        if (dealerPoint > participantPoint) {
            return LOSE;
        }
        if (dealerPoint < participantPoint) {
            return WIN;
        }
        return DRAW;
    }

    public static Map<Participant, ParticipantResult> calculateParticipantResults(final Dealer dealer, final Participants participants) {
        Map<Participant, ParticipantResult> participantResults = new HashMap<>();
        for (Participant participant : participants.getParticipants()) {
            ParticipantResult participantResult = ParticipantResult.of(dealer, participant);
            participantResults.put(participant, participantResult);
        }
        return participantResults;
    }

    public static Map<ParticipantResult, Integer> countResults(final Map<Participant, ParticipantResult> participantResults) {
        return participantResults.values().stream()
                        .collect(Collectors.toMap(value -> value, value -> 1, Integer::sum,
                                () -> new HashMap<>(Map.of(WIN, 0, DRAW, 0, LOSE, 0))));
    }
}
