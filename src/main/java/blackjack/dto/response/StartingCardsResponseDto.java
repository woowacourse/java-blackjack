package blackjack.dto.response;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;

import java.util.List;
import java.util.stream.Collectors;

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

    public String getDealerName() {
        return dealer.name();
    }

    public String getPlayerNames() {
        return players().stream()
                .map(GamerDto::name)
                .collect(Collectors.joining(", "));
    }
}
