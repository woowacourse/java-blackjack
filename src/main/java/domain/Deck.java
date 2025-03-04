package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {

    private static final Deque<Card> CACHE = new ArrayDeque<>();

    static {
        List<Card> temp = new ArrayList<>();
        for (Symbol symbol : Symbol.getAllSymbols()) {
            for (Number number : Number.getAllNumbers()) {
                temp.add(new Card(symbol, number));
            }
        }

        Collections.shuffle(temp);
        CACHE.addAll(temp);
    }

    public static List<Card> provideCards(int size) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cards.add(CACHE.pollFirst());
        }
        return cards;
    }
}
