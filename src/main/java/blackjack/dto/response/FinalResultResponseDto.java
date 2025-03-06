package blackjack.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import blackjack.domain.RoundResult;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Players;
import blackjack.util.ListMerger;

public record FinalResultResponseDto(
    List<InnerGamer> gamers
) {

    // LATER TODO 리팩토링
    public static FinalResultResponseDto of(Dealer dealer, Players players) {
        InnerGamer convertedDealer = InnerGamer.from(dealer, new ArrayList<>(players.getPlayers()));
        List<InnerGamer> convertedPlayers = players.getPlayers().stream()
            .map(player -> InnerGamer.from(player, List.of(dealer)))
            .toList();
        return new FinalResultResponseDto(ListMerger.combine(convertedDealer, convertedPlayers));
    }

    public record InnerGamer(
        String name,
        Map<RoundResult, Integer> result
        ) {

        public static InnerGamer from(Gamer gamer, List<Gamer> otherGamers) {
            return new InnerGamer(gamer.getName(), gamer.getFinalResult(otherGamers));
        }
    }
}
