package dto;

import domain.participant.WinStatus;

import java.util.List;

public class PlayerInfoDto {
    private String name;
    private List<String> cards;
    private int score;
    private WinStatus winStatus;

    public PlayerInfoDto(String name, List<String> cards, int score, WinStatus winStatus) {
        this.name = name;
        this.cards = cards;
        this.score = score;
        this.winStatus = winStatus;
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
}
