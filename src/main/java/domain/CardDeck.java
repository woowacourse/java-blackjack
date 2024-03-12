package domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> deck;

    private CardDeck() {
        List<Card> cards = Suit.getValues().stream()
                .flatMap(mark -> Rank.getValues().stream()
                        .map(letter -> new Card(letter, mark)))
                .toList();
        deck = new Stack<>();
        deck.addAll(cards);
    }

    public static CardDeck createShuffledDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffle();
        return cardDeck;
    }

    public static CardDeck createNotShuffledDeck() {
        return new CardDeck();
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public Card draw() {
        return deck.pop();
    }
}
