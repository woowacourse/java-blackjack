package blackjack.model.game;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckInitializer {

    public Deck generateDeck() {
        List<Card> cards = Stream.of(CardShape.values())
                .flatMap(generateAllTypeCard())
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private Function<CardShape, Stream<Card>> generateAllTypeCard() {
        return cardShape -> Stream.of(CardType.values()).map(cardType -> new Card(cardShape, cardType));
    }
}
