package domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards;
    private boolean blackJack;

    public Cards(final List<Card> cards) {
        validateDuplicated(cards);
        this.cards = cards;
        this.blackJack = isTwentyOne();
    }

    public int sumOfCards() {
        if (isOddSum()) {
            int sum = calculateOddCardsSum();
            return checkOver21AndContainsA(sum);
        }
        int sum = 0;
        for (int i = 0; i < cards.size(); i += 2) {
            sum += cards.get(i).sum(cards.get(i + 1));
        }
        return checkOver21AndContainsA(sum);
    }

    private int checkOver21AndContainsA(final int sum) {
        if (sum > 21 && containsA()) {
            return sum - 10;
        }
        return sum;
    }

    private boolean containsA() {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    private int calculateOddCardsSum() {
        int sum = cards.get(0).sum(new Card("조커", 0));
        for (int i = 1; i < cards.size(); i += 2) {
            sum += cards.get(i).sum(cards.get(i + 1));
        }
        return sum;
    }

    private boolean isOddSum() {
        return cards.size() % 2 != 0;
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

    public boolean isTwentyOne() {
        return sumOfCards() == 21;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public List<String> getCards() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }
}
