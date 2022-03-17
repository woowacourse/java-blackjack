package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import java.util.List;

public class Score {

    private static final int ACE_ADDITIONAL_NUMBER = 10;
    private static final int BUST_THRESHOLD = 21;

    private int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score calculate(List<Card> cards) {
        int sum = cards.stream()
                .map(Card::getNumber)
                .mapToInt(Number::getScore)
                .sum();

        Score score = new Score(sum);
        cards.forEach(score::calculateBest);

        return score;
    }

    private void calculateBest(Card card) {
        if (card.isAce() && score + ACE_ADDITIONAL_NUMBER <= BUST_THRESHOLD) {
            score += 10;
        }
    }

    public boolean isBusted() {
        return score > BUST_THRESHOLD;
    }

    public int getScore() {
        return score;
    }
}
