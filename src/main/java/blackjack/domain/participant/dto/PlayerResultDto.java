package blackjack.domain.participant.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.card.Card;
import java.util.List;

public record PlayerResultDto(PlayerDto playerDto, int score) {
    public static PlayerResultDto from(Player player) {
        return new PlayerResultDto(PlayerDto.from(player), player.getScoreValue());
    }

    public String getName() {
        return playerDto.name();
    }

    public List<Card> getCards() {
        return playerDto.cards();
    }
}
