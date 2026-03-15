package dto;

import domain.participant.Player;
import util.CardMapper;

import java.util.List;

public record PlayerHandScoreDto(String name, List<String> handCards, int score, boolean isBust, boolean isBlackJack) {

    public static PlayerHandScoreDto from(Player player) {
        return new PlayerHandScoreDto(
                player.getName(), player.getHandCards().stream()
                .map(CardMapper::cardToKorean)
                .toList(), player.getScore(), player.isBust(), player.isBlackJack());
    }

}
