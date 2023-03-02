package domain.deck;

import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class CardDeck {

    private static final int DRAW_CARD_INDEX = 0;

    private final List<Card> cards;

    CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck shuffledFullCardDeck() {
        final List<Card> cards = makeTotalCards();
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private static List<Card> makeTotalCards() {
        return stream(CardShape.values())
                .flatMap(shape ->
                        stream(CardValue.values())
                                .map(value -> new Card(shape, value))
                ).collect(toList());
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public Card draw() {
        return this.cards.remove(DRAW_CARD_INDEX);
    }
}
