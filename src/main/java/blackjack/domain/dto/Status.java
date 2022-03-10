package blackjack.domain.dto;

import java.util.List;

public class Status {

    private final String name;
    private final List<CardDto> cardDtos;
    private final int score;

    public Status(String name, List<CardDto> cardDtos, int score) {
        this.name = name;
        this.cardDtos = cardDtos;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }

    public int getScore() {
        return score;
    }
}
