package dto;

import domain.model.Player;
import java.util.List;

public record PlayerDeckDto(
        String playerName,
        List<CardDto> cardDtos
) {
    public static PlayerDeckDto of(Player player) {
        String name = player.getName();
        List<CardDto> cardDtos = player.getCards().stream()
                .map(CardDto::of)
                .toList();
        return new PlayerDeckDto(name, cardDtos);
    }
}
