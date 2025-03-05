package blackjack.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import blackjack.domain.RoundResult;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public record FinalResultResponseDto(
    List<InnerGamer> gamers
) {

    public static FinalResultResponseDto of(Dealer dealer, List<Player> players) {
        InnerGamer convertedDealer = InnerGamer.from(dealer, new ArrayList<>(players));
        List<InnerGamer> convertedPlayers = players.stream()
            .map(player -> InnerGamer.from(player, List.of(dealer)))
            .toList();
        List<InnerGamer> result = new ArrayList<>();
        result.add(convertedDealer);
        result.addAll(convertedPlayers);
        return new FinalResultResponseDto(result);
    }

    public record InnerGamer(
        String name,
        Map<RoundResult, Integer> result
        ) {

        public static InnerGamer from(Gamer gamer, List<Gamer> otherGamers) {
            return new InnerGamer(getNameOf(gamer), gamer.getFinalResult(otherGamers));
        }

        private static String getNameOf(Gamer gamer) {
            if (gamer instanceof Player player) {
                return player.getName();
            }
            return "딜러";
        }
    }
}
