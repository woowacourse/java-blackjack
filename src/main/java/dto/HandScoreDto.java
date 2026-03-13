package dto;

import domain.participant.Participant;
import util.CardMapper;

import java.util.List;

public record HandScoreDto(String name, List<String> handCards, String score) {

    public static HandScoreDto from(Participant participant) {
        return new HandScoreDto(
                participant.getName(), participant.getHandCards().stream()
                .map(CardMapper::cardToKorean)
                .toList(), getStringScore(participant));
    }

    private static String getStringScore(Participant participant) {
        return String.valueOf(participant.getScore().getValue());
    }
}
