package domain;

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
        TrumpCardType[] trumpCardTypes = TrumpCardType.values();

        for (TrumpCardType trumpCardType : trumpCardTypes) {
            addCardsOfShape(deck, trumpCardType);
        }
    }

    private void addCardsOfShape(Stack<Card> deck, TrumpCardType trumpCardType) {
        TrumpCardNumber[] trumpCardNumbers = TrumpCardNumber.values();

        for (TrumpCardNumber trumpCardNumber : trumpCardNumbers) {
            deck.add(new Card(trumpCardType, trumpCardNumber));
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
