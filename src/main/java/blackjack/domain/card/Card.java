package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public class Card {

    private static final Map<String, Card> CACHE = new HashMap<>();

    private final CardNumber number;
    private final CardType type;


    private Card(CardNumber number, CardType type) {
        this.type = type;
        this.number = number;
    }

    public static Card of(CardNumber number, CardType type) {
        return CACHE.computeIfAbsent(number.getOriginalName() + type.getName(), key -> new Card(number, type));
    }

    public int getNumber() {
        return number.getNumber();
    }

    public String getCardText() {
        return number.getOriginalName() + type.getName();
    }
}
