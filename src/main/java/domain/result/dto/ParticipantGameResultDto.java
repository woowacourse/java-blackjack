package domain.result.dto;

import domain.participant.Participant;
import domain.participant.dto.ParticipantHandDto;
import domain.participant.dto.ParticipantHandDtoMapper;

public record ParticipantGameResultDto(ParticipantHandDto playerHand, int resultScore) {
    public static ParticipantGameResultDto from(Participant participant) {
        int resultScore = participant.getResultScore();
        return new ParticipantGameResultDto(ParticipantHandDtoMapper.map(participant), resultScore);
    }
}
