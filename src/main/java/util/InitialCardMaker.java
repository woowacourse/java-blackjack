package util;

import domain.Card;
import type.Shape;
import type.Letter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitialCardMaker {

    private InitialCardMaker() {
    }

    private static final List<Card> initialCards;

    static {
        initialCards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            Arrays.stream(Letter.values())
                    .forEach(value -> initialCards.add(Card.of(shape, value)));
        }
    }

    public static List<Card> generate() {
        return new ArrayList<>(initialCards);
    }

}
