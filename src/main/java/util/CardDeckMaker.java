package util;

import domain.Card;
import domain.Shape;
import domain.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeckMaker {

    public static List<Card> generate() {
        List<Card> cards = new ArrayList<>();

        for (Shape shape : Shape.values()) {
            Arrays.stream(Value.values())
                    .forEach(value -> cards.add(new Card(shape, value)));
        }

        return cards;
    }

}
