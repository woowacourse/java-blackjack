package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_POINT_DIFFERENCE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isSameBlackJackScore() {
        return calculateScore() == BLACKJACK_SCORE;
    }

    public int calculateScore() {
        if (containsAce(cards)) {
            return sumWithAce();
        }

        return getSumPoint();
    }

    private boolean containsAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int sumWithAce() {
        int sum = getSumPoint();

        if (sum + ACE_POINT_DIFFERENCE <= BLACKJACK_SCORE) {
            sum = sum + ACE_POINT_DIFFERENCE;
        }

        validateNegative(sum);
        
        return sum;
    }

    public int getSumPoint() {
        int sum = cards.stream()
                .mapToInt(Card::getPoint)
                .sum();

        validateNegative(sum);

        return sum;
    }

    private void validateNegative(int score) {
        if (score < 0) {
            throw new RuntimeException("점수 계산에 문제가 있습니다.");
        }
    }

    public boolean isDefaultSize() {
        return cards.size() == 2;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> getLimitedCard(int size) {
        return Collections.unmodifiableList(cards.subList(0, size));
    }
}
