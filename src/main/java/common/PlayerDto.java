package common;

import java.util.List;

public class PlayerDto {
    private String name;
    private int bettingMoney;
    private List<String> cards;
    private int score;
    private int profit;

    public void setName(String name) {
        this.name = name;
    }

    public void setBettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    private PlayerDto() {}

    private PlayerDto(String name, int bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private PlayerDto(String name, int bettingMoney, List<String> cards) {
        this.name = name;
        this.bettingMoney = bettingMoney;
        this.cards = cards;
    }

    private PlayerDto(String name, int bettingMoney, List<String> cards, int score, int profit) {
        this.name = name;
        this.bettingMoney = bettingMoney;
        this.cards = cards;
        this.score = score;
        this.profit = profit;
    }

    public static PlayerDto init() {
        return new PlayerDto();
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    public static PlayerDto input(String name, int bettingMoney) {
        return new PlayerDto(name, bettingMoney);
    }

    public static PlayerDto complete(String name, int bettingMoney, List<String> cards, int score, int profit) {
        return new PlayerDto(name, bettingMoney, cards, score, profit);
    }

    public static PlayerDto update(String name, int bettingMoney, List<String> cards) {
        return new PlayerDto(name, bettingMoney, cards);
    }


    public int getBettingMoney() {
        return bettingMoney;
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