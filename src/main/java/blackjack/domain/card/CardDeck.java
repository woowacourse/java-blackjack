package blackjack.domain.card;

import static java.util.stream.Collectors.toList;

import blackjack.exception.NoMoreCardException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private final Deque<Card> cards;

    private CardDeck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck create() {
        List<Card> cards = Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Symbol.values()).map(symbol -> new Card(shape, symbol)))
                .collect(toList());
        Collections.shuffle(cards);
        return new CardDeck(new ArrayDeque<>(cards));
    }

    public Card pick() {
        validateCardExist();
        return cards.remove();
    }

    private void validateCardExist() {
        if (cards.isEmpty()) {
            throw new NoMoreCardException();
        }
    }
}
