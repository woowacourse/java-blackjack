package dto;

import domain.participant.Participant;
import java.util.List;

public record ParticipantDto(String name, List<String> cards, int totalSum) {

    public static ParticipantDto from(final Participant player) {
        return new ParticipantDto(player.getName(), player.getCardNames(), player.handsSum());
    }
}
