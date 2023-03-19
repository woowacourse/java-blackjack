package dto;

import java.util.Map;

public class ResultDto {
    private final Map<String, Integer> playerResult;
    private final int dealerResult;

    private ResultDto(Map<String, Integer> playerResult, int dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public static ResultDto of (Map<String, Integer> playerResult, int dealerResult){
        return new ResultDto(playerResult, dealerResult);
    }

    public Map<String, Integer> getPlayerResult(){
        return playerResult;
    }

    public int getDealerResult(){
        return dealerResult;
    }
}
