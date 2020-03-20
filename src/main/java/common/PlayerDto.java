package common;

import java.util.List;

public class PlayerDto {
    private String name;
    private int bettingMoney;
    private List<String> cards;
    private int score;
    private int profit;

    private PlayerDto() {}

    private PlayerDto(String name, int bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static PlayerDto init() {
        return new PlayerDto();
    }

    public static PlayerDto input(String name, int bettingMoney) {
        return new PlayerDto(name, bettingMoney);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setBettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }


    public int getBettingMoney() {
        return bettingMoney;
    }

    public int getProfit() {
        return profit;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}