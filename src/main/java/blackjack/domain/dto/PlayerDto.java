package blackjack.domain.dto;

import blackjack.domain.Player;
import blackjack.domain.card.Card;
import java.util.List;

public record PlayerDto(String name, List<Card> cards) {
    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), player.getCards());
    }
}
