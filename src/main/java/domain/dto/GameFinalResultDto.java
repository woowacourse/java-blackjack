package domain.dto;

import domain.GameResult;
import domain.participant.Dealer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GameFinalResultDto {
    private final DealerResultDto dealerResult;
    private final Map<String, Integer> playerResults;

    private GameFinalResultDto(DealerResultDto dealerResult, Map<String, Integer> playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public static GameFinalResultDto of(Dealer dealer, GameResult gameResult) {
        DealerResultDto dealerResult = DealerResultDto.of(dealer, gameResult);
        Map<String, Integer> playerResults = new LinkedHashMap<>();
        gameResult.getPlayerResults().forEach(
                (player, profit) -> playerResults.put(player.getName(), profit.getAmount()) 
        );

        return new GameFinalResultDto(dealerResult, playerResults);
    }

    public DealerResultDto getDealerResult() {
        return dealerResult;
    }

    public Map<String, Integer> getPlayerResults() {
        return playerResults;
    }
}
