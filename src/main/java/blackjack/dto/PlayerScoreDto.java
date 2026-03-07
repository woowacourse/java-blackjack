package blackjack.dto;

import blackjack.model.Player;
import java.util.List;

public record PlayerScoreDto(
        String playerName,
        List<CardDto> cards,
        int score
) {
    public static PlayerScoreDto from(Player player) {
        List<CardDto> cards = player.getCards()
                .stream()
                .map(CardDto::from)
                .toList();

        return new PlayerScoreDto(
                player.getName(),
                cards,
                player.getScore()
        );
    }
}
