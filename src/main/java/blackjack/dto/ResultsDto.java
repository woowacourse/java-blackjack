package blackjack.dto;

import java.util.List;

public class ResultsDto {
    private String dealerName;
    private double dealerProfit;
    private int playersSize;
    private List<String> playersNames;
    private List<Double> playersProfits;

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public void setDealerProfit(double dealerProfit) {
        this.dealerProfit = dealerProfit;
    }

    public void setPlayersSize(int playersSize) {
        this.playersSize = playersSize;
    }

    public void setPlayersNames(List<String> playersNames) {
        this.playersNames = playersNames;
    }

    public void setPlayersProfits(List<Double> playersProfits) {
        this.playersProfits = playersProfits;
    }

    public String getDealerName() {
        return dealerName;
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public int getPlayersSize() {
        return playersSize;
    }

    public List<String> getPlayersNames() {
        return playersNames;
    }

    public List<Double> getPlayersProfits() {
        return playersProfits;
    }
}
