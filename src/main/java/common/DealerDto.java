package common;

import java.util.List;

public class DealerDto {
    private String name = "딜러";
    private List<String> cards;
    private int score;

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    private int profit;


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
