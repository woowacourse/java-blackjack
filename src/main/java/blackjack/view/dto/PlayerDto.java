package blackjack.view.dto;

import java.util.List;

public class PlayerDto {

    private List<CardDto> cardList;
    private String name;
    private int score;

    public PlayerDto(List<CardDto> cardList, String name, int score) {
        this.cardList = cardList;
        this.name = name;
        this.score = score;
    }

    public List<CardDto> getCardList() {
        return cardList;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
