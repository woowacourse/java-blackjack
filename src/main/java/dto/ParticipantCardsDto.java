package dto;

import domain.Participant;
import java.util.List;

public record ParticipantCardsDto(String name, List<String> cardsInfo, int totalScore) {
    public static ParticipantCardsDto from(Participant participant) {
        return new ParticipantCardsDto(participant.getName(), participant.getCardsInfo(), participant.getScore());
    }
};
