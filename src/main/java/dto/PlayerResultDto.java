package dto;

import domain.model.Card;
import domain.model.Player;

import java.util.ArrayList;
import java.util.List;

public record PlayerResultDto(
        String playerName,
        List<CardDto> cardDtos,
        int sum,
        String isWin
) {
    public static PlayerResultDto of(Player player) {
        List<Card> cards = player.getDeck().getCards();
        List<CardDto> playerCards = new ArrayList<>();
        for (Card card : cards) {
            playerCards.add(CardDto.of(card));
        }
        return new PlayerResultDto(player.getName(), playerCards, player.getFinalSum(), player.getPlayerStatus().getName());
    }
}
