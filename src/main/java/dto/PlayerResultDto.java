package dto;

import domain.participant.WinStatus;
import domain.vo.Money;

import java.util.List;

public class PlayerResultDto {
    private final String name;
    private final List<String> cards;
    private final int score;
    private final WinStatus winStatus;
    private final Money profit;

    public PlayerResultDto(String name, List<String> cards, int score, WinStatus winStatus, Money profit) {
        this.name = name;
        this.cards = cards;
        this.score = score;
        this.winStatus = winStatus;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public WinStatus getWinStatus() {
        return winStatus;
    }

    public Money getProfit() {
        return profit;
    }
}
