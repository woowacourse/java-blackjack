package blackjack.dto;

import blackjack.domain.Player;

import java.util.List;

public record PlayerScoreDto(
        String name,
        List<CardDto> cards,
        int score
) {
    public static PlayerScoreDto from(Player player) {
        List<CardDto> cardDtos = player.getCards().stream()
                .map(CardDto::from)
                .toList();
        return new PlayerScoreDto(player.getName(), cardDtos, player.score());
    }
}
