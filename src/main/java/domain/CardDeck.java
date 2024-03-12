package domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> deck = new Stack<>();

    private CardDeck() {
        List<Card> cards = Suit.getValues().stream()
                .flatMap(suit -> Rank.getValues().stream()
                        .map(rank -> new Card(rank, suit)))
                .toList();
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
        Collections.shuffle(this.deck);
    }

    public Card draw() {
        return deck.pop();
    }
}
