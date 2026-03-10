package dto;

import java.util.List;

public record PlayerDeckDto(
        String playerName,
        List<CardDto> cardDtos
) {
    public static PlayerDeckDto of(String playerName, List<CardDto> cardDtos) {
        return new PlayerDeckDto(playerName, cardDtos);
    }
}
