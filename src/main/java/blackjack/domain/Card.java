package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Card {

    static final List<Card> cachingCard = new ArrayList<>();
    private final CardPattern pattern;
    private final CardNumber number;

    static {
        for (CardPattern pattern : CardPattern.values()) {
            for (CardNumber number : CardNumber.values()) {
                cachingCard.add(new Card(pattern, number));
            }
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
