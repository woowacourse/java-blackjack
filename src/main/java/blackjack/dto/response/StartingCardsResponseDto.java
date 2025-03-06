package blackjack.dto.response;

import java.util.List;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;
import blackjack.dto.GamerDto;

public record StartingCardsResponseDto(
    int startingCardsSize,
    GamerDto dealer,
    List<GamerDto> players
) {

    public static StartingCardsResponseDto of(Dealer dealer, Players players) {
        return new StartingCardsResponseDto(
            players.getPlayers().getFirst().getCardCount(),
            GamerDto.from(dealer),
            players.getPlayers().stream()
                .map(GamerDto::from)
                .toList()
        );
    }
}
