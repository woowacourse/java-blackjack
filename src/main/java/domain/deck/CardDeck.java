package domain.deck;

import domain.card.Card;
import domain.card.CardShape;
import domain.card.CardValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardDeck {

    private final List<Card> cards;

    CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck shuffledFullCardDeck() {
        List<Card> cards = new ArrayList<>();
        for (final CardShape cardShape : CardShape.values()) {
            cards.addAll(Arrays.stream(CardValue.values())
                    .map(it -> new Card(cardShape, it))
                    .collect(Collectors.toList()));
        }
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public Card draw() {
        return this.cards.remove(0);
    }
}
