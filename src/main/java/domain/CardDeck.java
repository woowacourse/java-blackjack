package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> deck;

    public CardDeck() {
        List<Card> cards = Suit.getValues().stream()
                .flatMap(mark -> Rank.getValues().stream()
                        .map(letter -> new Card(letter, mark)))
                .toList();
        deck = new Stack<>();
        deck.addAll(cards);
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public Hand initHand() {
        List<Card> initialCards = new ArrayList<>(List.of(draw(), draw()));
        return new Hand(initialCards);
    }

    public Card draw() {
        return deck.pop();
    }
}
