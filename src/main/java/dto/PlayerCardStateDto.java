package dto;

import domain.gamer.Player;
import java.util.List;

public record PlayerCardStateDto(String name, List<String> playerCards) {
    public static PlayerCardStateDto makePlayerCardState(Player player) {
        String playerName = player.getName().getValue();
        List<String> playerCards = player.getCardStatus();
        return new PlayerCardStateDto(playerName, playerCards);
    }
}
