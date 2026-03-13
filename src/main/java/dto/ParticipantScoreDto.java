package dto;

import domain.participant.Participant;
import java.util.List;

public record ParticipantScoreDto(String name, List<String> cardNames, int score) {
    public static ParticipantScoreDto from(Participant participant) {
        return new ParticipantScoreDto(
                participant.getName(),
                List.copyOf(participant.getCardNames()),
                participant.getScore().getGameScore()
        );
    }
}
