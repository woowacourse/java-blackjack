package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private final List<Card> cardDeck;

    public CardDeck() {
        List<Card> cards = Arrays.stream(Symbol.values())
                                 .flatMap(this::generateCard)
                                 .collect(Collectors.toList());
        Collections.shuffle(cards);
        this.cardDeck = cards;
    }

    private Stream<Card> generateCard(Symbol symbol) {
        return Arrays.stream(Shape.values())
                     .map(shape -> new Card(symbol, shape));
    }

    public int size() {
        return cardDeck.size();
    }

}
