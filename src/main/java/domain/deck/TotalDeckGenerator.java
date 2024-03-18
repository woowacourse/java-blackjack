package domain.deck;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TotalDeckGenerator {
    public static TotalDeck generate() {
        List<Shape> shapes = Shape.giveShapes();
        List<Number> numbers = Number.giveNumbers();
        List<Card> cards = shapes.stream()
                .flatMap(shape -> numbers.stream()
                        .map(number -> new Card(shape, number)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new TotalDeck(cards);
    }
}
