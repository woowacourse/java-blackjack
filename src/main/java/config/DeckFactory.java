package config;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Number;
import domain.card.Shape;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeckFactory {

    public Deck create() {
        List<Card> cards;
        cards = Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Number.values())
                        .map(number -> new Card(shape, number)))
                .collect(Collectors.toList());

        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
