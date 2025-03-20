package blackjack.dto.response;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;

import java.util.List;

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

        @Override
        public String toString() {
            return String.format("%s - 결과: %d", gamer, sumOfCards);
        }
    }
}
