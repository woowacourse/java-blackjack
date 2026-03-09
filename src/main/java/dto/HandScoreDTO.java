package dto;

import domain.card.Card;
import domain.participant.Participant;

import java.util.List;

public record HandScoreDTO(String name, List<String> handCards, String score) {

    public static HandScoreDTO from(Participant participant) {
        return new HandScoreDTO(
                participant.getName(), participant.getHandCards().stream()
                .map(Card::toString)
                .toList(), getStringScore(participant));
    }

    private static String getStringScore(Participant participant) {
        if (participant.isBust()) {
            return "버스트";
        }
        return String.valueOf(participant.calculateScore());
    }
}
