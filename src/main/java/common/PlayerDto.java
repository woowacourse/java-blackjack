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

    private PlayerDto(String name, int bettingMoney, List<String> cards, int score) {
        this(name, bettingMoney);
        this.cards = cards;
        this.score = score;
    }

    private PlayerDto(String name, int bettingMoney, List<String> cards, int score, int profit) {
        this(name, bettingMoney, cards, score);
        this.profit = profit;
    }

    public static PlayerDto input(String name, int bettingMoney) {
        return new PlayerDto(name, bettingMoney);
    }

    public static PlayerDto of(String name, int bettingMoney, List<String> cards, int score) {
        return new PlayerDto(name, bettingMoney, cards, score);
    }

    public static PlayerDto of(String name, int bettingMoney, List<String> cards, int score, int profit) {
        return new PlayerDto(name, bettingMoney, cards, score, profit);
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