package dto;

import domain.model.Dealer;
import domain.model.Deck;
import domain.model.Player;
import domain.service.CardDistributor;
import java.util.ArrayList;
import java.util.List;

public record InitialDto (
        CardDto dealerCard,
        List<PlayerDeckDto> playerDeckDtos
) {
    public static InitialDto of(Dealer dealer, List<Player> players) {
        CardDto dealerCard = CardDto.of(dealer.getLastCard());
        List<PlayerDeckDto> playersDeckDto = players.stream()
                .map(PlayerDeckDto::of)
                .toList();

        return new InitialDto(dealerCard, playersDeckDto);
    }
}
