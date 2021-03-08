package blackjack.domain.user;

import blackjack.domain.card.Card;
import java.util.List;

public class Hand {
    public static final int MAX_SCORE = 21;
    public static final int BLACK_JACK_CARD_SIZE = 2;

    private List<Card> cards;
    private int score;
    private HandStatus status;

    public Hand(List<Card> cards) {
        this.cards = cards;
        this.score = calculateHandScore();
        this.status = calculateStatus();
    }

    public void addCard(Card card) {
        cards.add(card);
        score = calculateHandScore();
        status = calculateStatus();
    }

    public int calculateHandScore() {
        int currentScore = calculateMaxScore();
        if (isUnderBustScore(currentScore)) {
            return currentScore;
        }

        return recalculateAce(currentScore);
    }

    private int calculateMaxScore() {
        return cards.stream()
            .mapToInt(card -> card.getDenomination().getScore())
            .sum();
    }

    private boolean isUnderBustScore(int sum) {
        return sum <= MAX_SCORE;
    }

    private int recalculateAce(int currentScore) {
        int countAce = countAce();
        if (countAce <= 0) {
            return currentScore;
        }
        return convertAceToOne(countAce, currentScore);
    }

    private int countAce() {
        return (int) cards.stream()
            .filter(card -> card.getDenomination().isAce())
            .count();
    }

    private int convertAceToOne(int countAce, int sum) {
        while (countAce > 0 && !isUnderBustScore(sum)) {
            sum -= 10;
            countAce--;
        }
        return sum;
    }

    private HandStatus calculateStatus() {
        if (score == MAX_SCORE && cards.size() == BLACK_JACK_CARD_SIZE) {
            return HandStatus.BLACK_JACK;
        }

        if (score > MAX_SCORE) {
            return HandStatus.BUST;
        }

        return HandStatus.HIT;
    }

    public boolean isSameStatus(HandStatus handStatus) {
        return status == handStatus;
    }

    public boolean isGreaterScoreThan(int score) {
        return this.score > score;
    }

    public boolean isLessScoreThan(int score) {
        return this.score < score;
    }

    public boolean isSameScore(int score) {
        return this.score == score;
    }

    public void convertStatusToStay() {
        status = HandStatus.STAY;
    }

    public HandStatus getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public List<Card> getCards() {
        return cards;
    }
}
