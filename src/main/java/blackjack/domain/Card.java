package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Card {

    private static final List<Card> cachingCard = new ArrayList<>();

    private final CardPattern pattern;
    private final CardNumber number;

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

    private Card(CardPattern pattern, CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    public static Card of(CardPattern pattern, CardNumber number) {
        return cachingCard.stream()
            .filter(card -> card.isContain(pattern, number))
            .findAny()
            .orElse(null);
    }

    public static List<Card> createDeck() {
        return new LinkedList<>(cachingCard);
    }

    public boolean isContain(CardPattern pattern, CardNumber number) {
        return this.pattern.equals(pattern) && this.number.equals(number);
    }

    public int getNumber() {
        return number.getValue();
    }

    public String getPattern() {
        return pattern.getName();
    }
}
