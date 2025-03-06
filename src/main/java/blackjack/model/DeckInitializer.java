package blackjack.model;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckInitializer {

    public Deck generateDeck() {
        List<Card> cards = Stream.of(CardShape.values())
                .flatMap(generateCard())
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Deck(cards);

    }

    private static Function<CardShape, Stream<? extends Card>> generateCard() {
        return cardShape -> Stream.of(CardType.values()).map(cardType -> new Card(cardShape, cardType));
    }
}
