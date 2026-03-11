package domain.participant.dto;

import domain.participant.Participant;
import java.util.List;

public record ParticipantHandDto(String playerName, List<String> handOnCards) {
    public static ParticipantHandDto from(Participant participant) {
        return new ParticipantHandDto(participant.toDisplayMyName(), participant.disPlayMyCardBundle());
    }
}
