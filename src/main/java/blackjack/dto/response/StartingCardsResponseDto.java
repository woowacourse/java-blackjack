package blackjack.dto.response;

import java.util.List;

import blackjack.domain.GameManager;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;

public record StartingCardsResponseDto(
    int startingCardsSize,
    GamerDto dealer,
    List<GamerDto> players
) {

    public static StartingCardsResponseDto of(Dealer dealer, List<Player> players) {
        return new StartingCardsResponseDto(
            GameManager.STARTING_CARDS_SIZE,
            GamerDto.dealerFrom(dealer),
            players.stream()
                .map(GamerDto::from)
                .toList()
        );
    }
}
