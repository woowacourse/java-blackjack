package domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int MAXINUM_SUM_OF_CARD = 21;
    private static final int TREAT_A_AS_ONE = 10;

    private final List<Card> cards;
    private final boolean blackJack;

    public Cards(final List<Card> cards) {
        validateDuplicated(cards);
        this.cards = cards;
        this.blackJack = isMaximumNumber();
    }

    public int sumOfCards() {
        if (isOddSum()) {
            int sum = calculateOddCardsSum();
            return checkOver21AndContainsA(sum);
        }
        int sum = calculateSum();
        return checkOver21AndContainsA(sum);
    }

    private int calculateSum() {
        int sum = 0;
        for (int i = 0; i < cards.size(); i += 2) {
            sum += cards.get(i).sum(cards.get(i + 1));
        }
        return sum;
    }

    private int checkOver21AndContainsA(final int sum) {
        if (sum > MAXINUM_SUM_OF_CARD && containsA()) {
            return sum - TREAT_A_AS_ONE;
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
            int duplicatedCardsNum = cards.size() - size + 1;
            throw new IllegalArgumentException(duplicatedCardsNum + "개의 카드가 중복됩니다");
        }
    }

    public boolean isMaximumNumber() {
        return sumOfCards() == MAXINUM_SUM_OF_CARD;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public List<String> copyCards() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return cards;
    }
}
