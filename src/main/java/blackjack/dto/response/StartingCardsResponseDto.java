package blackjack.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Players;
import blackjack.dto.GamerDto;

public record StartingCardsResponseDto(
    int startingCardsSize,
    GamerDto dealer,
    List<GamerDto> players
) {

    public static StartingCardsResponseDto of(Dealer dealer, Players players) {
        return new StartingCardsResponseDto(
            Gamer.STARTING_CARDS_SIZE,
            GamerDto.dealerFrom(dealer),
            players.getPlayers().stream()
                .map(GamerDto::from)
                .toList()
        );
    }

    public String getDealerName() {
        return dealer.name();
    }

    public String getPlayerNames() {
        return players().stream()
            .map(GamerDto::name)
            .collect(Collectors.joining(", "));
    }
}
