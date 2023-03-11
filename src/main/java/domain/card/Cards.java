package domain.card;

import domain.card.Card;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACKJACK_CONDITION = 21;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateDuplicated(cards);
        this.cards = cards;
    }

    public int sumOfCards() {
        int sum = cards.stream()
                .map(Card::getValue)
                .mapToInt(i -> i)
                .sum();
        return checkOver21AndContainsA(sum);
    }

    private int checkOver21AndContainsA(final int sum) {
        if (sum > BLACKJACK_CONDITION && containsA()) {
            return sum - 10;
        }
        return sum;
    }

    private boolean containsA() {
        return cards.stream()
                .anyMatch(Card::isACE);
    }

    public boolean addCard(final Card otherCard) {
        if (isNotContains(otherCard)) {
            cards.add(otherCard);
            return true;
        }
        return false;
    }

    private boolean isNotContains(final Card otherCard) {
        return !cards.contains(otherCard);
    }

    private void validateDuplicated(final List<Card> cards) {
        int size = new HashSet<>(cards).size();
        if (cards.size() != size) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && isTwentyOne();
    }

    private boolean isTwentyOne() {
        return sumOfCards() == BLACKJACK_CONDITION;
    }

    public List<String> cardsToString() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public boolean isNotBurst() {
        return sumOfCards() < 22;
    }
}
