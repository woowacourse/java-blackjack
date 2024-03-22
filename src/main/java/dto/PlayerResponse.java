package dto;

import domain.player.Player;
import java.util.List;

public record PlayerResponse(String name, List<CardResponse> cardResponse, Integer score) {
    public static PlayerResponse of(final Player player) {
        return new PlayerResponse(player.getName(), player.getHands().stream().map(CardResponse::of).toList(),
                player.getScore());
    }
}
