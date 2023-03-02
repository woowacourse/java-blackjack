package blackjack.dto;

import java.util.List;

public class CardDto {

    private final List<String> cardNames;
    private final int score;

    public CardDto(List<String> cardNames, int score) {
        this.cardNames = cardNames;
        this.score = score;
    }

    public List<String> getCardNames() {
        return cardNames;
    }

    public int getScore() {
        return score;
    }
}
