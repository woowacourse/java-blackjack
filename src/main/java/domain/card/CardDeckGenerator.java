package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CardDeckGenerator {

    private CardDeckGenerator() {
    }

    public static List<Card> generateCardDeck() {
        List<TrumpNumber> numbers = Arrays.stream(TrumpNumber.values()).toList();
        List<TrumpShape> shapes = Arrays.stream(TrumpShape.values()).toList();
        List<Card> cards = new ArrayList<>(numbers.stream()
                .flatMap(number -> shapes.stream().map(shape -> Card.of(number, shape)))
                .toList());
        Collections.shuffle(cards);
        return cards;
    }
}
