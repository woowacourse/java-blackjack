package blackjackgame.dto;

import java.util.List;

public class GameProfitDTO {
    private final String dealerName;
    private final List<String> playersName;
    private final Integer dealerProfit;
    private final List<Integer> playersProfit;

    public GameProfitDTO(String dealerName, List<String> playersName, double dealerProfit, List<Double> playersProfit) {
        this.dealerName = dealerName;
        this.playersName = playersName;
        this.dealerProfit = (int) dealerProfit;
        this.playersProfit = playersProfit.stream()
                .map(Double::intValue)
                .toList();
    }

    public String getDealerName() {
        return dealerName;
    }

    public List<String> getPlayersName() {
        return playersName;
    }

    public Integer getDealerProfit() {
        return dealerProfit;
    }

    public List<Integer> getPlayersProfit() {
        return playersProfit;
    }
}
