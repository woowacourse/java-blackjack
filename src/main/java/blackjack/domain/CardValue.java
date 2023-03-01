package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public enum CardValue {
    SYMBOL(List.of("heart", "diamond", "spade", "clover")),
    NUMBER(List.of("2", "3", "4", "5", "6", "7", "8", "9", "A", "K", "J", "Q"));

    private final List<String> values;

    CardValue(List<String> values) {
        this.values = values;
    }

    public static List<Card> makeCards() {
        List<Card> cards = new ArrayList<>();
        for (String symbol : SYMBOL.values) {
            for (String number : NUMBER.values) {
                cards.add(new Card(symbol, number));
            }
        }
        return cards;
    }
}
