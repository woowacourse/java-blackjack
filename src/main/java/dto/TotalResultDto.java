package dto;

import java.util.List;
import java.util.Map;

public class TotalResultDto {
    private final Map<String, List<String>> dealerMatchResult;
    private final Map<String, String> playersMatchResult;

    public TotalResultDto(Map<String, List<String>> dealerMatchResult, Map<String, String> playersMatchResult) {
        this.dealerMatchResult = dealerMatchResult;
        this.playersMatchResult = playersMatchResult;
    }

    public Map<String, String> getPlayersMatchResult() {
        return playersMatchResult;
    }

    public Map<String, List<String>> getDealerMatchResult() {
        return dealerMatchResult;
    }
}
