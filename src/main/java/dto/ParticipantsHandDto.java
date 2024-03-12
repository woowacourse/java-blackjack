package dto;

import domain.Participant;
import domain.Player;

import java.util.List;

public record ParticipantsHandDto(List<ParticipantHandDto> playerHands) {

    public static ParticipantsHandDto from(List<Participant> participants) {
        List<ParticipantHandDto> participantHandDtos = participants.stream()
                .map(participant -> ParticipantHandDto.from((Player) participant))
                .toList();

        return new ParticipantsHandDto(participantHandDtos);
    }
}
