package blackjack.domain.card;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.exception.NoMoreCardException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    private CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck create() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Symbol.values()).map(symbol -> new Card(shape, symbol)))
                .collect(collectingAndThen(toList(), CardDeck::new));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pick() {
        validateCardExist();
        return cards.remove(0);
    }

    private void validateCardExist() {
        if (cards.isEmpty()) {
            throw new NoMoreCardException();
        }
    }
}
