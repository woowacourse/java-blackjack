package dto;

import domain.participant.Participant;
import util.CardMapper;

import java.util.List;

public record HandScoreDto(String name, List<String> handCards, int score, boolean isBust, boolean isBlackJack) {

    public static HandScoreDto from(Participant participant) {
        return new HandScoreDto(
                participant.getName(), participant.getHandCards().stream()
                .map(CardMapper::cardToKorean)
                .toList(), participant.getScore(), participant.getStatus().getCards().isBust(), participant.getStatus().getCards().isBlackJack());
    }

}
