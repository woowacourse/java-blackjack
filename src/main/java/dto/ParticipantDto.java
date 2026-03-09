package dto;

import domain.Card;
import domain.Participant;
import java.util.List;

public record ParticipantDto(
        String name,
        String cards,
        String score
) {

    public static ParticipantDto from(String name, Participant participant) {
        return new ParticipantDto(name, cardsToString(participant.getHand()), scoreToString(participant));
    }

    private static String cardsToString(List<Card> hand) {
        return String.join(", ", hand.stream().map(Card::toString).toList());
    }

    private static String scoreToString(Participant participant) {
        if (participant.isBust()) {
            return "버스트";
        }
        return String.valueOf(participant.calculateScore());
    }
}
