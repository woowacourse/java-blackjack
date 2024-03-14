package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;
import java.util.List;
import java.util.Map;

public record ParticipantScoresDto(List<ParticipantScoreDto> participantScores) {
    public static ParticipantScoresDto of(final Map<ParticipantName, Hands> handResult,
                                          final Map<ParticipantName, Score> scoreResult) {
        List<ParticipantScoreDto> participantScores = handResult.entrySet().stream()
                .map(entry -> ParticipantScoreDto.of(entry.getKey(), entry.getValue(), scoreResult.get(entry.getKey())))
                .toList();

        return new ParticipantScoresDto(participantScores);
    }
}
