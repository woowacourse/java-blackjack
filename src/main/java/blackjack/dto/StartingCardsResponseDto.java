package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public record StartingCardsResponseDto(
    int startingCardsSize,
    InnerGamer dealer,
    List<InnerGamer> players
) {

    public static StartingCardsResponseDto of(Dealer dealer, List<Player> players) {
        return new StartingCardsResponseDto(
            players.getFirst().getCardCount(),
            InnerGamer.from(dealer),
            players.stream()
                .map(InnerGamer::from)
                .toList()
        );
    }

    public record InnerGamer(
        String name,
        List<CardDto> cards
    ) {

        private static InnerGamer from(Gamer gamer) {
            return new InnerGamer(
                gamer.getName(),
                gamer.getCards().stream()
                    .map(CardDto::from)
                    .toList());
        }
    }
}
