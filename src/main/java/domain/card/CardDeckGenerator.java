package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CardDeckGenerator {

    private CardDeckGenerator() {
    }

    public static List<Card> generateCardDeck() {
        List<TrumpNumber> numbers = List.of(TrumpNumber.values());
        List<TrumpShape> shapes = List.of(TrumpShape.values());
        List<Card> cards = new ArrayList<>(numbers.stream()
                .flatMap(number -> shapes.stream().map(shape -> Card.of(number, shape)))
                .toList());
        Collections.shuffle(cards);
        return cards;
    }
}
