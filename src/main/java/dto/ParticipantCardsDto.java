package dto;

import domain.participant.Participant;
import java.util.List;

public record ParticipantCardsDto(
        String name,
        List<String> cardsInfo,
        int totalScore) {
    public static ParticipantCardsDto from(Participant participant) {
        return new ParticipantCardsDto(
                participant.getParticipantName(),
                participant.getCardsInfo(),
                participant.getScore()
        );
    }
}
