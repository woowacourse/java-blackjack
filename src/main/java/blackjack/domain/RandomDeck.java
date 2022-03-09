package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomDeck implements Deck {

    private static List<Card> cards;

    public RandomDeck() {
        init();
    }

    @Override
    public Card pick() {
        if (cards.isEmpty()) {
            init();
        }
        Collections.shuffle(cards);
        return cards.remove(cards.size() - 1);
    }

    private void init() {
        cards = new ArrayList<>();
        for (CardNumber number : CardNumber.values()) {
            Arrays.stream(Type.values())
                    .forEach(type -> cards.add(new Card(number, type)));
        }
    }
}
