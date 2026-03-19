package dto;

import domain.participant.WinStatus;
import domain.vo.Money;

import java.util.List;
import java.util.Map;

public class DealerResultDto {
    private final String name = "딜러";
    private final List<String> cards;
    private final int score;
    private final Map<WinStatus, Integer> record;
    private final Money profit;

    public DealerResultDto(List<String> cards, int score, Map<WinStatus, Integer> record, Money profit) {
        this.cards = cards;
        this.score = score;
        this.record = record;
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

    public Map<WinStatus, Integer> getRecord() {
        return record;
    }

    public Money getProfit() {
        return profit;
    }
}
