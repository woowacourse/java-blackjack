package blackjack.dto.response;

import java.util.List;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Players;
import blackjack.dto.CardDto;

public record RoundResultsResponseDto(
    InnerGamer dealer,
    List<InnerGamer> players
) {

    public static RoundResultsResponseDto of(Dealer dealer, Players players) {
        return new RoundResultsResponseDto(
            InnerGamer.from(dealer),
            players.getPlayers().stream()
                .map(InnerGamer::from)
                .toList()
        );
    }

    // TODO: name, cards -> GamerDto로 분리할지 고민해보기
    public record InnerGamer(
        String name,
        List<CardDto> cards,
        int sumOfCards
    ) {

        private static InnerGamer from(Gamer gamer) {
            return new InnerGamer(
                gamer.getName(),
                gamer.getCards().stream()
                    .map(CardDto::from)
                    .toList(),
                gamer.getSumOfCards());
        }
    }
}
