package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {
    private static final int BLACKJACK = 21;
    private static final int ACE_SCORE_CRITERIA = 11;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int TWO = 2;

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(Card... values) {
        List<Card> cards = Arrays.stream(values)
                .collect(Collectors.toList());
        return new Cards(cards);
    }

    public static Cards of(List<Card> values) {
        return new Cards(values);
    }

    public int calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (hasAce() && totalScore <= ACE_SCORE_CRITERIA) {
            totalScore += ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK && cards.size() == TWO;
    }
}
