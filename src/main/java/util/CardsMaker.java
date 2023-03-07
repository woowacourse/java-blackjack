package util;

import domain.Card;
import domain.Cards;
import type.Shape;
import type.Letter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardsMaker {

    private static final List<Card> cards;

    static {
        cards = new ArrayList<>();

        for (Shape shape : Shape.values()) {
            Arrays.stream(Letter.values())
                    .forEach(value -> cards.add(new Card(shape, value)));
        }
    }

    public static Cards generate() {
        return new Cards(new ArrayList<>(cards));
    }

}
