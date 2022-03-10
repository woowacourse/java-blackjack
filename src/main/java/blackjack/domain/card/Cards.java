package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;

    private final Collection<Card> cards = new ArrayList<>();

    public static Cards of(List<Card> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException("첫 카드는 두장");
        }
        Cards instance = new Cards();
        instance.add(cards.get(0));
        instance.add(cards.get(1));
        return instance;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getValue)
                .sum();

        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        score = handleAce(score, aceCount);
        return score;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    private int handleAce(int score, int aceCount) {
        while (isAbleToAddAdditionalAceValue(score, aceCount)) {
            score += Card.ADDITIONAL_ACE_VALUE;
            aceCount--;
        }
        return score;
    }

    private boolean isAbleToAddAdditionalAceValue(int score, int aceCount) {
        int expectedScore = score + Card.ADDITIONAL_ACE_VALUE;
        return aceCount > 0 && expectedScore <= BLACKJACK_SCORE;
    }

    public List<Card> toList() {
        return new ArrayList<>(cards);
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_SCORE;
    }
}
