package blackjack.domain.dto;

import java.util.Map;

public class GameResultDto {
    private final Map<String, ResultDto> userResult;
    private final Map<ResultDto, Integer> dealerResult;
    private final String dealerName;

    public GameResultDto(Map<String, ResultDto> userResult, Map<ResultDto, Integer> dealerResult, String dealerName) {
        this.userResult = userResult;
        this.dealerResult = dealerResult;
        this.dealerName = dealerName;
    }

    public Map<String, ResultDto> getUserResult() {
        return userResult;
    }

    public Map<ResultDto, Integer> getDealerResult() {
        return dealerResult;
    }

    public String getDealerName() {
        return dealerName;
    }
}
