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
            String name;
            name = getNameOf(gamer);
            return new InnerGamer(
                name,
                gamer.getCards().stream()
                    .map(CardDto::from)
                    .toList());
        }

        // TODO 지우게 될 것
        private static String getNameOf(Gamer gamer) {
            if (gamer instanceof Player player) {
                return player.getName();
            }
            return "딜러";
        }
    }
}
