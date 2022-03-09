package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HitResultDto {

    private final String name;
    private final List<CardDto> cards;
    private final int score;

    public HitResultDto(String name, List<CardDto> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
