package model.result;

import java.util.List;

public record ScoreDto(CardDto card, int score) {

    public List<String> cards() {
        return card.cards();
    }
}
