package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BUST = 22;
    private static final int BLACKJACK = 21;
    public static final int DEFAULT_INIT_SIZE = 2;
    public static final int ACE_POINT = 10;
    public static final int MINIMUM_POINT_FOR_ACE = 11;

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
        if (hasAce() && sumOfPoint <= MINIMUM_POINT_FOR_ACE) {
            sumOfPoint += ACE_POINT;
        }
        return sumOfPoint;
    }

    public Cards add(Card card) {
        final List<Card> newValue = new ArrayList<>(cards);
        newValue.add(card);
        return new Cards(newValue);
    }

    public boolean isBlackJack() {
        return BLACKJACK == sum() && size() == DEFAULT_INIT_SIZE;
    }

    public boolean isBust() {
        return sum() >= BUST;
    }

    public boolean isReady() {
        return cards.size() < DEFAULT_INIT_SIZE;
    }

    public boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public int size() {
        return this.cards.size();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
