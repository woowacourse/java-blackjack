package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createDeck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                .flatMap(Cards::combinate)
                .toList();
        return new Cards(cards);
    }

    private static Stream<Card> combinate(CardShape shape) {
        return Arrays.stream(CardValue.values())
                .map(value -> new Card(shape, value));
    }

    public int size() {
        return cards.size();
    }

    public Cards shuffle() {
        ArrayList<Card> newCards = new ArrayList<>(cards);
        Collections.shuffle(newCards);
        return new Cards(newCards);
    }

    public boolean compareTo(Cards shuffleDeck) {
        for (int i = 0; i < cards.size(); i++) {
            if(!shuffleDeck.cards.get(i).equals(cards.get(i)))
                return false;
        }
        return true;
    }

    public Card pickCard() {
        return cards.removeFirst();
    }
}
