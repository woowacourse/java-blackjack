package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public record StartingCardsResponseDto(
    int startingCardsSize,
    GamerDto dealer,
    List<GamerDto> players
) {

    public static StartingCardsResponseDto of(Dealer dealer, List<Player> players) {
        return new StartingCardsResponseDto(
            players.getFirst().getCardCount(),
            GamerDto.from(dealer),
            players.stream()
                .map(GamerDto::from)
                .toList()
        );
    }
}
