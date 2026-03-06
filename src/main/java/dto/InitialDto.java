package dto;

import domain.model.Card;
import domain.model.Dealer;
import domain.model.Player;

import java.util.ArrayList;
import java.util.List;

public record InitialDto (
        CardDto dealerCard,
        List<PlayerDeckDto> playerDeckDtos
) {
    public static InitialDto of(Dealer dealer, List<Player> players) {
        CardDto dealerCard = CardDto.of(dealer.getDeck().getLastCard());
        List<PlayerDeckDto> playerDeckDtos = new ArrayList<>();
        for (Player player : players) {
            List<Card> cards = player.getDeck().getCards();
            List<CardDto> cardDtos = cards.stream()
                    .map(CardDto::of)
                    .toList();
            playerDeckDtos.add(PlayerDeckDto.of(player.getName(), cardDtos));
        }
        return new InitialDto(dealerCard, playerDeckDtos);
    }
}
