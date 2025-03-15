package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck implements CardProvider {

    private final Deque<Card> deck = new ArrayDeque<>();

    public Deck() {
        List<Card> temp = new ArrayList<>();
        for (Symbol symbol : Symbol.getAllSymbols()) {
            addAllNumbers(symbol, temp);
        }

        Collections.shuffle(temp);
        deck.addAll(temp);
    }

    private void addAllNumbers(Symbol symbol, List<Card> temp) {
        for (domain.card.Number number : Number.getAllNumbers()) {
            temp.add(new Card(symbol, number));
        }
    }

    public List<Card> provideCards(int size) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cards.add(deck.pollFirst());
        }
        return cards;
    }
}
