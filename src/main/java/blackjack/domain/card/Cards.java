package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BUST = 22;
    private static final int BLACKJACK = 21;
    private static final int DEFAULT_INIT_SIZE = 2;
    private static final int ACE_POINT = 10;
    private static final int MINIMUM_POINT_FOR_ACE = 11;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> value) {
        this.cards = value;
    }

    public int sum() {
        int sumOfPoint = cards.stream()
            .mapToInt(Card::point)
            .sum();
        return calculateAcePoint(sumOfPoint);
    }

    public Cards add(Card card) {
        final List<Card> newValue = new ArrayList<>(cards);
        newValue.add(card);
        return new Cards(newValue);
    }

    public boolean isBlackJack() {
        return BLACKJACK == sum() && isInitialSize();
    }

    public boolean isBust() {
        return sum() >= BUST;
    }

    public boolean isNotInitialized() {
        return cards.size() < DEFAULT_INIT_SIZE;
    }

    public boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isInitialSize() {
        return this.cards.size() == DEFAULT_INIT_SIZE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private int calculateAcePoint(int point) {
        if (hasAce() && point <= MINIMUM_POINT_FOR_ACE) {
            point += ACE_POINT;
        }
        return point;
    }
}
