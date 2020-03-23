package dto;

import java.util.*;

public class ResponseWinningResultDTO {
    private Map<String, Double> winningProfit;

    private ResponseWinningResultDTO(Map<String, Double> winningProfit) {
        this.winningProfit = winningProfit;
    }

    public static ResponseWinningResultDTO create(Map<String, Double> winningProfit) {
        return new ResponseWinningResultDTO(winningProfit);
    }

    public Map<String, Double> getWinningProfit() {
        return this.winningProfit;
    }
}
