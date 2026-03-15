package dto;

import domain.participant.Player;
import util.HandCardProcessor;

import java.util.List;

public record PlayerHandDto(String name, List<String> handCards) {

    public static PlayerHandDto from(Player player) {
        return new PlayerHandDto(
                player.getName(),
                HandCardProcessor.processHandCards(player.getHandCards())
        );
    }
}
