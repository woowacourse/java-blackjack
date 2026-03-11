package dto;

import domain.player.Player;
import java.util.List;

public record PlayerCardInfo(
        List<String> card,
        int score
) {
    public static PlayerCardInfo from(Player player) {
        return new PlayerCardInfo(player.getCardInfo(), player.score());
    }
}
