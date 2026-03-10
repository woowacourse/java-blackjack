package dto;

import domain.card.Card;
import domain.participant.Participant;
import java.util.List;

public record ParticipantDto(
        String name,
        List<Card> cards,
        String score
) {

    public static ParticipantDto of(String name, Participant participant) {
        return new ParticipantDto(name, participant.getHand(), scoreToString(participant));
    }

    private static String scoreToString(Participant participant) {
        if (participant.isBust()) {
            return "버스트";
        }
        return String.valueOf(participant.calculateScore());
    }
}
