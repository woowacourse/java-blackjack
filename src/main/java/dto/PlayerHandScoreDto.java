package dto;

import domain.participant.Player;
import util.HandCardProcessor;

import java.util.List;

public record PlayerHandScoreDto(String name, List<String> handCards, int score, boolean isBust, boolean isBlackJack) {

    public static PlayerHandScoreDto from(Player player) {
        return new PlayerHandScoreDto(
                player.getName(),
                HandCardProcessor.processHandCards(player.getHandCards()),
                player.getScore(),
                player.isBust(),
                player.isBlackJack()
        );
    }
}
