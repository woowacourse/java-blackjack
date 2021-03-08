package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardsGenerator {

    private CardsGenerator() {
    }

    public static List<Card> generateShuffledCards() {
        List<Card> cards = Arrays.stream(Symbol.values())
                .flatMap(CardsGenerator::generateCardsWithEachSymbol)
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return cards;
    }

    private static Stream<Card> generateCardsWithEachSymbol(Symbol symbol) {
        return Arrays.stream(Shape.values())
                .map(shape -> new Card(symbol, shape));
    }
}
