package blackjack.domain.card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Card {

    private static final List<Card> cachingCard = new ArrayList<>();

    private final CardPattern pattern;
    private final CardNumber number;

    private Card(CardPattern pattern, CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    static {
        for (CardPattern pattern : CardPattern.values()) {
            addAllNumberOf(pattern);
        }
    }

    private static void addAllNumberOf(CardPattern pattern) {
        for (CardNumber number : CardNumber.values()) {
            cachingCard.add(new Card(pattern, number));
        }
    }

    public static Card of(CardPattern pattern, CardNumber number) {
        return cachingCard.stream()
            .filter(card -> card.isEqualTo(pattern, number))
            .findAny()
            .orElse(null);
    }

    public boolean isEqualTo(CardPattern pattern, CardNumber number) {
        return this.pattern.equals(pattern) && this.number.equals(number);
    }

    public static List<Card> createDeck() {
        return new LinkedList<>(cachingCard);
    }

    public String getPatternName() {
        return pattern.getName();
    }

    public CardNumber getCardNumber() {
        return number;
    }
}
