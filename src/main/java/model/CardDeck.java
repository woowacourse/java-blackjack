package model;

import java.util.ArrayList;
import java.util.List;

public final class CardDeck {

    private static final List<String> cards;

    static {
        cards = initCards();
    }

    private static List<String> initCards() {
        final String[] suitType = new String[]{"다이아몬드", "하트", "스페이드", "클로버"};
        final String[] rankType = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        final List<String> cards = new ArrayList<>();
        for (String suit : suitType) {
            for (String rank : rankType) {
                cards.add(rank + suit);
            }
        }
        return cards;
    }

    private CardDeck() {
    }

    public static List<String> getCards() {
        return cards;
    }
}
