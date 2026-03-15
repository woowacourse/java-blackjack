package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.result.Score;
import java.util.List;

public record ParticipantScoreDto(
    String participantName,
    List<CardNameDto> cards,
    Score score
) {
    public static ParticipantScoreDto from(Participant participant, Score score) {
        List<CardNameDto> cards = participant.getCards().stream()
            .map(CardNameDto::from)
            .toList();
        return new ParticipantScoreDto(participant.getName(), cards, score);
    }
}
