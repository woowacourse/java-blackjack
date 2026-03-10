package model;

import dto.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeckFactory {

    private CardDeckFactory() {}

    public static List<Card> createShuffledDeck() {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Shape.values()).forEach(shape ->
            Arrays.stream(CardNumber.values()).forEach(number ->
                cards.add(new Card(shape, number))
            )
        );
        Collections.shuffle(cards);
        return cards;
    }
}

