package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomDeck implements Deck {
    private static final RandomDeck INSTANCE = new RandomDeck();
    private static final Random RANDOM = new Random();

    private final Map<Integer, Number> numberByOrder = new HashMap<>();
    private final Map<Integer, Shape> shapeByOrder = new HashMap<>();

    private RandomDeck() {
        for (Number number : Number.values()) {
            numberByOrder.put(numberByOrder.size(), number);
        }
        for (Shape cardShape : Shape.values()) {
            shapeByOrder.put(shapeByOrder.size(), cardShape);
        }
    }

    public static RandomDeck getInstance() {
        return INSTANCE;
    }

    @Override
    public Card drawCard() {
        int orderOfNumber = RANDOM.nextInt(numberByOrder.size());
        int orderOfShape = RANDOM.nextInt(shapeByOrder.size());

        Number number = numberByOrder.get(orderOfNumber);
        Shape shape = shapeByOrder.get(orderOfShape);

        return new Card(number, shape);
    }
}
