package dto;

import java.util.Map;

public class ResultDTO {
    private final Map<String, Integer> playerResult;
    private final int dealerResult;

    public ResultDTO(Map<String, Integer> playerResult, int dealerResult) {
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
