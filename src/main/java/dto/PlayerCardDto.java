package dto;

import domain.player.Player;
import java.util.List;

public record PlayerCardDto(
        String name,
        List<String> card,
        int score
) {
    public static PlayerCardDto from(Player player) {
        return new PlayerCardDto(player.getName(), player.cards(), player.score());
    }
}
