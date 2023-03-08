package blackjack.domain.card;

import blackjack.domain.card.exception.NoMoreCardException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private final List<Card> cards;

    private CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck create() {
        List<Card> cards = Arrays.stream(Shape.values())
                .flatMap(shape ->
                        Arrays.stream(Number.values()).map(number -> new Card(shape, number)))
                .collect(Collectors.toList());
        return new CardDeck(cards);
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
