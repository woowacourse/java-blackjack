package blackjack.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.dto.RoundResultDto;
import blackjack.util.ListMerger;

public record FinalResultResponseDto(
    List<InnerGamer> gamers
) {

    public static FinalResultResponseDto of(Dealer dealer, List<Player> players) {
        InnerGamer convertedDealer = InnerGamer.from(dealer, new ArrayList<>(players));
        List<InnerGamer> convertedPlayers = players.stream()
            .map(player -> InnerGamer.from(player, List.of(dealer)))
            .toList();
        return new FinalResultResponseDto(ListMerger.combine(convertedDealer, convertedPlayers));
    }

    public record InnerGamer(
        String name,
        Map<RoundResultDto, Integer> result
    ) {

        public static InnerGamer from(Gamer gamer, List<Gamer> otherGamers) {
            return new InnerGamer(
                gamer.getName(),
                gamer.getFinalResult(otherGamers).entrySet().stream()
                    .collect(Collectors.toMap(set -> RoundResultDto.from(set.getKey()), Map.Entry::getValue))
            );
        }
    }
}
