package common;

import java.util.List;

public class DealerDto {
    private String name;
    private List<String> cards;
    private int score;
    private int profit;

    public DealerDto(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    private DealerDto(String name, List<String> cards, int profit, int score) {
        this.name = name;
        this.cards = cards;
        this.profit = profit;
        this.score = score;
    }

    public static DealerDto complete(String name, List<String> cards, int profit, int score) {
        return new DealerDto(name, cards, profit, score);
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

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    public List<String> getCards() {
        return cards;
    }
}
