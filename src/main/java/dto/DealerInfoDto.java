package dto;

import domain.participant.WinStatus;

import java.util.List;
import java.util.Map;

public class DealerInfoDto {
    private final String name = "딜러";
    private final List<String> cards;
    private final int score;
    private final Map<WinStatus, Integer> record;

    public DealerInfoDto(List<String> cards, int score, Map<WinStatus, Integer> record) {
        this.cards = cards;
        this.score = score;
        this.record = record;
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
}
