package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import java.util.List;

public class Score {

    private static final int ACE_ADDITIONAL_NUMBER = 10;
    private static final int BLACKJACK_CARD_HAND_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BUST_THRESHOLD = 21;

    private int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score calculate(List<Card> cards) {
        Score score = new Score(calculateWorst(cards));
        cards.forEach(score::calculateBest);
        return score;
    }

    private static int calculateWorst(List<Card> cards) {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(Number::getScore)
                .sum();
    }

    private void calculateBest(Card card) {
        if (card.isAce() && score + ACE_ADDITIONAL_NUMBER <= BUST_THRESHOLD) {
            score += 10;
        }
    }

    public boolean isBusted() {
        return score > BUST_THRESHOLD;
    }

    public boolean isBlackJack(List<Card> cards) {
        return cards.size() == BLACKJACK_CARD_HAND_COUNT && score == BLACKJACK_SCORE;
    }

    public int getScore() {
        return score;
    }
}
