package view.viewer.card;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardViewer {
    private static final Map<Card, String> CARD_VIEWER = Arrays.stream(Shape.values())
            .flatMap(CardViewer::makeCardsByShape)
            .collect(Collectors.toMap(
                    Function.identity(),
                    card -> NumberViewer.show(card.number()) + ShapeViewer.show(card.shape())
            ));

    private static Stream<Card> makeCardsByShape(Shape shape) {
        return Arrays.stream(Number.values())
                .map(number -> new Card(shape, number));
    }

    public static String show(Card card) {
        return CARD_VIEWER.get(card);
    }
}
