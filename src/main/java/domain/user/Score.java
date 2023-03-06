package domain.user;

import domain.card.Card;
import domain.CardNumber;
import java.util.List;
import java.util.stream.Collectors;

public class Score {
    private static final int BLACKJACK = 21;
    private static final int DEALER_MIN_SCORE = 17;
    private static final int ZERO = 0;
    private static final int ACE_ONE = 1;

    private int score;

    public Score() {
        this.score = 0;
    }

    public void setScore(List<Card> cards) {
        List<Integer> numbers = convertCardsToNumbers(cards);
        int aceCount = countOfAce(numbers);
        int totalScore = sum(numbers);

        if (totalScore > BLACKJACK) {
            totalScore = calculateAceAsOne(aceCount, totalScore);
        }
        score = totalScore;
    }

    private List<Integer> convertCardsToNumbers(List<Card> cards) {
        return cards.stream()
                .map(Card::getScore)
                .collect(Collectors.toUnmodifiableList());
    }

    private int countOfAce(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(number -> number == CardNumber.ACE.getScore())
                .count();
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(number -> number)
                .sum();
    }

    private int calculateAceAsOne(int aceCount, int score) {
        while (aceCount > ZERO && score > BLACKJACK) {
            score -= CardNumber.ACE.getScore();
            score += ACE_ONE;
            aceCount--;
        }
        return score;
    }

    public PlayerStatus calculatePlayerStatus() {
        if (score > BLACKJACK) {
            return PlayerStatus.BUST;
        }
        return PlayerStatus.NORMAL;
    }

    public DealerStatus calculateDealerStatus() {
        if (score > BLACKJACK) {
            return DealerStatus.BUST;
        }
        if (score < DEALER_MIN_SCORE) {
            return DealerStatus.UNDER_MIN_SCORE;
        }
        return DealerStatus.NORMAL;
    }

    public int getScore() {
        return score;
    }
}
