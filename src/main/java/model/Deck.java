package model;

import java.util.HashMap;
import java.util.Map;

public class Deck {
    private final Map<String, Integer> deck;

    public Deck() {
       deck = new HashMap<>(Map.of(
                "A하트", -1,
                "A클로버", -1,
                "A스페이드", -1,
                "A다이아", -1,
                "2하트", 2,
                "2클로버", 2,
                "2스페이드", 2,
                "2다이아", 2,
                "3하트", 3,
                "3클로버", 3
        ));
        deck.putAll(Map.of(
                "3스페이드", 3,
                "3다이아", 3,
                "4하트", 4,
                "4클로버", 4,
                "4스페이드", 4,
                "4다이아", 4,
                "5하트", 5,
                "5클로버", 5,
                "5스페이드", 5,
                "5다이아", 5
        ));
        deck.putAll(Map.of(
                "6하트", 6,
                "6클로버", 6,
                "6스페이드", 6,
                "6다이아", 6,
                "7하트", 7,
                "7클로버", 7,
                "7스페이드", 7,
                "7다이아", 7,
                "8하트", 8,
                "8클로버", 8
        ));
        deck.putAll(Map.of(
                "8스페이드", 8,
                "8다이아", 8,
                "9하트", 9,
                "9클로버", 9,
                "9스페이드", 9,
                "9다이아", 9,
                "10하트", 10,
                "10클로버", 10,
                "10스페이드", 10,
                "10다이아", 10
        ));
        deck.putAll(Map.of(
                "K하트", 10,
                "K클로버", 10,
                "K스페이드", 10,
                "K다이아", 10,
                "J하트", 10,
                "J클로버", 10,
                "J스페이드", 10,
                "J다이아", 10,
                "Q하트", 10,
                "Q클로버", 10
        ));
        deck.putAll(Map.of(
                "Q스페이드", 10,
                "Q다이아", 10
        ));
    }

    public int pick(String cardValue) {
        int value = deck.get(cardValue);
        deck.remove(cardValue);
        return value;
    }

    public int findValueByKey(String cardValue) {
        return deck.entrySet().stream()
                .filter(card -> card.getKey().equals(cardValue))
                .map(card -> card.getValue())
                .findFirst()
                .orElseThrow(
                        ()-> new IllegalArgumentException("[ERROR] 입력하신 카드가 존재하지 않습니다")
                );
    }
}
