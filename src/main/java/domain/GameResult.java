package domain;

import domain.constant.Result;
import domain.dto.DealerResultDto;
import domain.dto.GameFinalResultDto;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final DealerResultDto dealerResult;
    private final Map<Player, Result> playerResults;

    public GameResult(Dealer dealer, Collection<Player> players) {
        this.playerResults = determinePlayerResults(dealer, players);
        this.dealerResult = aggregateResult(dealer);
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

    private DealerResultDto aggregateResult(Dealer dealer) {
        int winCount = 0;
        int drawCount = 0;
        int loseCount = 0;

        for (Result result : playerResults.values()) {
            if (result == Result.LOSE) {
                winCount++;
                continue;
            }

            if (result == Result.WIN) {
                loseCount++;
                continue;
            }

            drawCount++;
        }

        return new DealerResultDto(dealer.getName(), winCount, drawCount, loseCount);
    }
}
