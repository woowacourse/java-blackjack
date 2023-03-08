package domain.card;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardShape;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private static final String DECK_IS_EMPTY = "덱이 비어있습니다.";

    private final Stack<Card> shuffledDeck;

    public Deck() {
        Stack<Card> deck = new Stack<>();

        addAllCards(deck);
        Collections.shuffle(deck);

        this.shuffledDeck = deck;
    }

    private void addAllCards(Stack<Card> deck) {
        CardShape[] shapes = CardShape.values();

        for (CardShape shape : shapes) {
            addCardsOfShape(deck, shape);
        }
    }

    private void addCardsOfShape(Stack<Card> deck, CardShape shape) {
        CardRank[] numbers = CardRank.values();

        for (CardRank number : numbers) {
            deck.add(Card.of(shape, number));
        }
    }

    public Card drawCard() {
        validateIsNotEmpty();

        return shuffledDeck.pop();
    }

    private void validateIsNotEmpty() {
        if (shuffledDeck.isEmpty()) {
            throw new IllegalStateException(DECK_IS_EMPTY);
        }
    }
}
