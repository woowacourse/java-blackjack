package blackjack.dto.response;

import java.util.List;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;

public record RoundResultsResponseDto(
    InnerGamer dealer,
    List<InnerGamer> players
) {

    public static RoundResultsResponseDto of(Dealer dealer, List<Player> players) {
        return new RoundResultsResponseDto(
            InnerGamer.from(dealer),
            players.stream()
                .map(InnerGamer::from)
                .toList()
        );
    }

    public record InnerGamer(
        GamerDto gamer,
        int sumOfCards
    ) {

        private static InnerGamer from(Gamer gamer) {
            return new InnerGamer(
                GamerDto.from(gamer),
                gamer.getSumOfCards());
        }
    }
}
