package blackjack.domain;

import fuel.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardFactory {
    private static final List<Card> cards = new ArrayList<>();

    static {
        Arrays.stream(Shape.values())
                .forEach(CardFactory::createByShape);
    }

    private static void createByShape(Shape shape) {
        Arrays.stream(Value.values())
                .forEach(value -> cards.add(new Card(shape, value)));
    }

    public static List<Card> make(){
        return Collections.unmodifiableList(cards);
    }
}
