package domain.participant.dto;

import domain.participant.Participant;
import java.util.List;

public record ParticipantHand(String playerName, List<String> handOnCards) {
    public static ParticipantHand from(Participant participant) {
        return new ParticipantHand(participant.toDisplayMyName(), participant.disPlayMyCardBundle());
    }
}
