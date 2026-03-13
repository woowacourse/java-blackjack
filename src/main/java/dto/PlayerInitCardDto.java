package dto;

import domain.player.Player;
import java.util.List;

public record PlayerInitCardDto(
        String name,
        List<String> card
) {
    public static PlayerInitCardDto from(Player player) {
        return new PlayerInitCardDto(player.getName(), player.getOpenCards());
    }
}
