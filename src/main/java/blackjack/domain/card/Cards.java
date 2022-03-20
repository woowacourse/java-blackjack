package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {

    private static final int ACE_GAP = 10;
    private static final int BLACKJACK_SIZE = 2;
    private static final int LIMIT_SCORE = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateDuplicate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateDuplicate(List<Card> cards) {
        Set<Card> duplicateCards = new HashSet<>(cards);

        if (cards.size() != duplicateCards.size()) {
            throw new IllegalArgumentException("중복된 카드가 존재할 수 없습니다.");
        }
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = calculateMinimumScore();
        if (hasAce() && score + ACE_GAP <= LIMIT_SCORE) {
            return score + ACE_GAP;
        }
        return score;
    }

    private int calculateMinimumScore() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore() == LIMIT_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > LIMIT_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
