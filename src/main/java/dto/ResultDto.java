package dto;

import java.util.Map;

public class ResultDto {
    private final Map<String, Integer> playerResult;
    private final int dealerResult;

    public ResultDto(Map<String, Integer> playerResult, int dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public Map<String, Integer> getPlayerResult(){
        return playerResult;
    }

    public int getDealerResult(){
        return dealerResult;
    }
}
