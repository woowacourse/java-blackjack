package blackjack.domain;

import java.util.HashMap;
import java.util.Map;

public class Card {

    private final CardPattern pattern;
    private final CardNumber number;

    private static final Map<String, Card> cards = new HashMap<>(52);

    static {
        for (CardPattern cardPattern : CardPattern.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards
                    .put(cardPattern.name() + cardNumber.name(), new Card(cardPattern, cardNumber));
            }
        }
    }

    private Card(CardPattern cardPattern, CardNumber cardNumber) {
        this.pattern = cardPattern;
        this.number = cardNumber;
    }

    public static Card valueOf(CardPattern cardPattern, CardNumber cardNumber) {
        return cards.get(cardPattern.name() + cardNumber.name());
    }

    public String getPatternAndNumber() {
        return number.getNumber() + pattern.getName();
    }

    public int givePoint() {
        return number.giveNumber();
    }

    public boolean isAce() {
        return number.isAce();
    }
}
