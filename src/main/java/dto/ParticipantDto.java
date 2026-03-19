package dto;

import domain.participant.Participant;
import java.util.List;

public record ParticipantDto(String name, List<String> cardNames) {
    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(
                participant.getName(),
                List.copyOf(participant.getCardNames())
        );
    }
}
