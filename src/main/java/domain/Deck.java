package domain;

import domain.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        init();
    }

    private void init() {
        for (Shape shape : Shape.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> cards.add(new Card(shape, rank)));
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
         return cards.remove(0);
    }

    public int getCardCount() {
        return cards.size();
    }
}
