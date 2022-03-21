package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public static Queue<Card> createDeck() {
        LinkedList<Card> cards = new LinkedList<>(cachingCard);
        Collections.shuffle(cards);
        return cards;
    }

    private boolean isContain(CardPattern pattern, CardNumber number) {
        return this.pattern.equals(pattern) && this.number.equals(number);
    }

    public int getNumberValue() {
        return number.getValue();
    }

    public String getNumberName() {
        return number.getName();
    }

    public String getPatternName() {
        return pattern.getName();
    }

    public boolean isAce() {
        return this.number == CardNumber.ACE;
    }
}
