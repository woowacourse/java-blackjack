package dto;

import domain.model.Card;
import domain.model.Player;

import java.util.ArrayList;
import java.util.List;

public record PlayerResultDto(
        String playerName,
        List<CardDto> cardDtos,
        int sum,
        int finalMoney
) {
    public static PlayerResultDto of(Player player, int finalScore) {
        List<Card> cards = player.getDeck().getCards();
        List<CardDto> playerCards = new ArrayList<>();
        for (Card card : cards) {
            playerCards.add(CardDto.of(card));
        }
        return new PlayerResultDto(player.getName(), playerCards, finalScore, player.getFinalMoney());
    }
}
