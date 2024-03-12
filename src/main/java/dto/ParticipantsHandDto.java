package dto;

import domain.Participant;

import java.util.List;

public record ParticipantsHandDto(List<ParticipantHandDto> playerHands) {

    public static ParticipantsHandDto from(List<Participant> participants) {
        List<ParticipantHandDto> participantHandDtos = participants.stream()
                .map(participant -> ParticipantHandDto.from(participant))
                .toList();

        return new ParticipantsHandDto(participantHandDtos);
    }
}
