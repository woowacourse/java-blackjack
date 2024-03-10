package blackjack.domain.card;

import java.util.*;

public class RandomDeck implements Deck {
    private static final RandomDeck INSTANCE = new RandomDeck();
    private static final Random RANDOM = new Random();

    private final List<Number> numbers;
    private final List<Shape> shapes;

    private RandomDeck() {
        this.numbers = Arrays.asList(Number.values());
        this.shapes = Arrays.asList(Shape.values());
    }

    public static RandomDeck getInstance() {
        return INSTANCE;
    }

    @Override
    public Card drawCard() {
        int orderOfNumber = RANDOM.nextInt(numbers.size());
        int orderOfShape = RANDOM.nextInt(shapes.size());

        Number number = numbers.get(orderOfNumber);
        Shape shape = shapes.get(orderOfShape);

        return new Card(number, shape);
    }
}
