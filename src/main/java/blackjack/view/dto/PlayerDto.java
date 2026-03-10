package blackjack.view.dto;

import blackjack.model.participant.Player;
import java.util.List;

public record PlayerDto(
        String playerName,
        List<CardDto> cards
) {
    public static PlayerDto from(Player player) {
        List<CardDto> cards = player.getCards()
                .stream()
                .map(CardDto::from)
                .toList();

        return new PlayerDto(player.getName(), cards);
    }
}
