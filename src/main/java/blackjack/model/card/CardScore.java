package blackjack.model.card;

import java.util.List;

public class CardScore {
    private final List<CardNumber> numbers;

    public CardScore(List<CardNumber> numbers) {
        this.numbers = numbers;
    }

    public int smallScore() {
        int score = 0;
        for (CardNumber number : numbers) {
            score += number.getSmallScore();
        }
        return score;
    }

    public int bigScore() {
        int score = 0;
        for (CardNumber number : numbers) {
            score += number.getBigScore();
        }
        return score;
    }
}
