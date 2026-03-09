package dto;

import domain.participant.WinStatus;

import java.util.List;
import java.util.Map;

public class DealerInfoDto {
    private String name;
    private List<String> cards;
    private int score;
    private Map<WinStatus, Integer> record;

    public DealerInfoDto(String name, List<String> cards, int score, Map<WinStatus, Integer> record) {
        this.name = name;
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
