package blackjack.domain.dto;

import java.util.Map;

public class ResponseProfitDto {

    private final int dealerProfit;
    private final Map<String, Integer> playersProfit;

    public ResponseProfitDto(int dealerProfit, Map<String, Integer> playersProfit) {
        this.dealerProfit = dealerProfit;
        this.playersProfit = playersProfit;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public Map<String, Integer> getPlayersProfit() {
        return playersProfit;
    }
}
