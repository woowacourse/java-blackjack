package dto;

import java.util.Map;

public class TotalResultDto {
    private final Map<String, String> playersMatchResult;
    private final Map<String, Long> dealerMatchCount;

    public TotalResultDto(Map<String, String> playersMatchResult, Map<String, Long> dealerMatchCount) {
        this.dealerMatchCount = dealerMatchCount;
        this.playersMatchResult = playersMatchResult;
    }

    public Map<String, Long> getDealerMatchCount() {
        return dealerMatchCount;
    }

    public Map<String, String> getPlayersMatchResult() {
        return playersMatchResult;
    }
}
