package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {

    public static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_SUBTRACT = 10;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateResult() {
        int maxScore = calculateMaxScore(cards);
        if (isUnderThreshold(maxScore)) {
            return maxScore;
        }
        return subtractAce(maxScore);
    }

    public int calculateMinScore() {
        return cards.stream()
                .mapToInt(Card::getCardMinNumber)
                .sum();
    }

    public void addAll(final Cards givenCards) {
        cards.addAll(givenCards.getCards());
    }

    private int calculateMaxScore(final List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getCardMaxNumber)
                .sum();
    }

    private boolean isUnderThreshold(final int score) {
        return score <= BLACKJACK_NUMBER;
    }

    private int subtractAce(int score) {
        int aceCount = countAce(cards);
        while (!isUnderThreshold(score) && aceCount-- > 0) {
            score -= ACE_SUBTRACT;
        }
        return score;
    }

    private int countAce(final List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Cards cards1)) {
            return false;
        }
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Cards getPartialCards(int start, int end) {
        return new Cards(cards.subList(start, end));
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public int getSize() {
        return cards.size();
    }
}
