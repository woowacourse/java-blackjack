package dto;

import domain.model.Card;
import domain.model.Player;

import java.util.ArrayList;
import java.util.List;

public record PlayerResultDto(
        String playerName,
        List<CardDto> cardDtos,
        int sum,
        boolean isWin
) {
    public static PlayerResultDto of(Player player, boolean isWin) {
        List<Card> cards = player.getDeck().getCards();
        List<CardDto> playerCards = new ArrayList<>();
        for (Card card : cards) {
            playerCards.add(CardDto.of(card));
        }
        return new PlayerResultDto(player.getName(), playerCards, player.getDeckSum(), true);
    }
}
