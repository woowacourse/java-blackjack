package domain.dto;

import domain.Card;
import domain.Player;
import java.util.List;

public record PlayerResponse(
        String name,
        List<Card> cards
) {
    public static PlayerResponse from(Player player) {
        return new PlayerResponse(player.getName(), player.getCards().getCards());
    }
}