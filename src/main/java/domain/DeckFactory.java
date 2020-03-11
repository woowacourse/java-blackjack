package domain;

import java.util.LinkedList;
import java.util.Queue;

public class DeckFactory {

    public static Deck createDeck() {
        Queue<Card> queue = new LinkedList<>();

        for (Symbol symbol : Symbol.values()) {
            queue.addAll(createCard(symbol));
        }

        return new Deck(queue);
    }

    private static Queue<Card> createCard(Symbol symbol) {
        Queue<Card> queue = new LinkedList<>();

        for (Type type : Type.values()) {
            queue.add(new Card(symbol, type));
        }

        return queue;
    }
}
