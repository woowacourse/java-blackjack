package blackjack.dto;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import java.util.List;

public record ParticipantScoreDtos(List<ParticipantScoreDto> scoreDtos) {

    public static ParticipantScoreDtos from(Participants participants) {
        List<ParticipantScoreDto> scoreDtos = participants.stream()
            .map(ParticipantScoreDtos::convertFrom)
            .toList();
        return new ParticipantScoreDtos(scoreDtos);
    }

    private static ParticipantScoreDto convertFrom(Participant participant) {
        return ParticipantScoreDto.from(participant, participant.getScore());
    }
}
