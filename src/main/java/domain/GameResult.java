package domain;

import domain.constant.Result;
import domain.dto.DealerResultDto;
import domain.dto.GameFinalResultDto;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, Result> playerResults;
    private final DealerResultDto dealerResult;

    public GameResult(Dealer dealer, Collection<Player> players) {
        this.playerResults = determinePlayerResults(dealer, players);
        this.dealerResult = aggregateDealerResult(dealer);
    }

    public GameFinalResultDto convertToDto() {
        Map<String, String> stringResults = new LinkedHashMap<>();
        playerResults.forEach(
                (player, result) -> stringResults.put(player.getName(), result.getName())
        );
        return new GameFinalResultDto(dealerResult, stringResults);
    }

    private Map<Player, Result> determinePlayerResults(Dealer dealer, Collection<Player> players) {
        Map<Player, Result> playerResults = new LinkedHashMap<>();

        for (Player player : players) {
            playerResults.put(player, Result.of(dealer, player));
        }

        return playerResults;
    }

    private DealerResultDto aggregateDealerResult(Dealer dealer) {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);

        for (Result playerResult : playerResults.values()) {
            Result reversedResult = playerResult.reverse();
            Integer count = dealerResult.getOrDefault(reversedResult, 0);
            dealerResult.put(reversedResult, ++count);
        }

        return DealerResultDto.of(dealer.getName(), dealerResult);
    }
}
