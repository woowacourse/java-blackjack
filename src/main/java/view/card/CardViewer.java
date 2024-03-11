package view.card;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardViewer {
    private static final Map<Card, String> CARD_VIEWER = Arrays.stream(Shape.values())
            .flatMap(shape -> Arrays.stream(Number.values())
                    .map(number -> new Card(shape, number)))
            .collect(Collectors.toMap(
                    Function.identity(),
                    card -> NumberViewer.show(card.number()) + ShapeViewer.show(card.shape())
            ));

    public static String show(Card card) {
        return CARD_VIEWER.get(card);
    }
}
