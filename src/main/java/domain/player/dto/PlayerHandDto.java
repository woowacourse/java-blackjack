package domain.player.dto;

import domain.player.Player;

import java.util.List;

public record PlayerHandDto(
        String playerName,
        List<String> handOnCards
) {
    public static PlayerHandDto of(Player player) {
        String playerName = player.toDisplayMyName();
        List<String> handOnCards = player.disPlayMyCardBundle();
        return new PlayerHandDto(playerName, handOnCards);
    }
}