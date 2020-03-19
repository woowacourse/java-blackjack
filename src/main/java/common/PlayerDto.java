package common;

import java.util.List;

public class PlayerDto {
    private String name;
    private int bettingMoney;
    private List<String> cards;
    private int score;
    private int profit;

    private PlayerDto(String name, int bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static PlayerDto of(String name, int bettingMoney) {
        return new PlayerDto(name, bettingMoney);
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
