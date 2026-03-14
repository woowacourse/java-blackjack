package dto;

import domain.participant.Player;
import util.CardMapper;

import java.util.List;

public record PlayerHandDto(String name, List<String> handCards) {

    public static PlayerHandDto from(Player player) {
        return new PlayerHandDto(
                player.getName(), player.getHandCards().stream()
                .map(CardMapper::cardToKorean)
                .toList());
    }
}
