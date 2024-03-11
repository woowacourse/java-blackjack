package domain;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TotalDeckGenerator {
    public static List<Card> generate() {
        List<Card> cards = Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Number.values())
                        .map(number -> new Card(shape, number)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return cards;
    }
}
