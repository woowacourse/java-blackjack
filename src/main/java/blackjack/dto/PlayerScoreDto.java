package blackjack.dto;

import blackjack.model.Player;
import blackjack.model.Score;
import java.util.List;

public record PlayerScoreDto(
        String playerName,
        List<CardDto> cards,
        Score score
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
