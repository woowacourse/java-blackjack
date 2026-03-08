package domain.dto;

import java.util.Map;
import java.util.Set;

public class GameFinalResultDto {
    private DealerResultDto dealerResult;
    private Map<String, String> playerResults;

    public GameFinalResultDto(DealerResultDto dealerResult, Map<String, String> playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public DealerResultDto getDealerResult() {
        return dealerResult;
    }

    public Set<Map.Entry<String, String>> getPlayerResults() {
        return playerResults.entrySet();
    }
}
