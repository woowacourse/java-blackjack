package blackjack.model;

import java.util.function.Function;
import java.util.stream.Stream;

public class DeckInitializer {

    public Deck generateDeck() {
        return new Deck(Stream.of(CardShape.values())
                .flatMap(generateCard())
                .toList()
        );
    }

    private static Function<CardShape, Stream<? extends Card>> generateCard() {
        return cardShape -> Stream.of(CardType.values()).map(cardType -> new Card(cardShape, cardType));
    }
}
