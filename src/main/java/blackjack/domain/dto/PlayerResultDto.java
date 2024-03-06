package blackjack.domain.dto;

import blackjack.domain.Player;
import blackjack.domain.card.Card;
import java.util.List;

public record PlayerResultDto(PlayerDto playerDto, int score) {
    public static PlayerResultDto from(Player player) {
        return new PlayerResultDto(PlayerDto.from(player), player.getScore());
    }

    public String getName() {
        return playerDto.name();
    }

    public List<Card> getCards() {
        return playerDto.cards();
    }
}
