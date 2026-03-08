package blackjack.dto.response;

import blackjack.domain.Player;

import java.util.List;

public record PlayerHandDto(
        String name,
        List<CardDto> cards
) {
    public static PlayerHandDto from(Player player) {
        List<CardDto> cardDtos = player.getCards().stream()
                .map(CardDto::from)
                .toList();
        return new PlayerHandDto(player.name(), cardDtos);
    }
}
