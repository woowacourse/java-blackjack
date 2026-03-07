package dto;

import domain.Player;
import java.util.List;

public record PlayerResultDto(
        List<String> cards,
        int score
) {
    public static PlayerResultDto from(Player player) {
        List<String> cardInfo = player.getHandCards().stream()
                .map(card -> card.getCardNumber().getSymbol() + card.getCardShape().getName())
                .toList();

        return new PlayerResultDto(cardInfo, player.getHand().getScore().getValue());
    }
}
