package dto;

import domain.participant.Participant;
import java.util.List;

public record ParticipantDto(String name, List<String> cards, int totalSum) {

    public static ParticipantDto from(final Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCardNames(), participant.handsSum());
    }
}
