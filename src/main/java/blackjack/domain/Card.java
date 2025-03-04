package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public record Card(String suit, String denomination) {

    private static final List<String> SUITS = List.of("스페이드", "클로버", "하트", "다이아몬드");
    private static final List<String> DENOMINATIONS = List.of("A", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "J", "Q", "K");

    private static final List<Card> CACHE = new ArrayList();

    static {
        for (String suit : SUITS) {
            for (String denomination : DENOMINATIONS) {
                CACHE.add(new Card(suit, denomination));
            }
        }
    }

    public static List<Card> values() {
        return CACHE;
    }
}
