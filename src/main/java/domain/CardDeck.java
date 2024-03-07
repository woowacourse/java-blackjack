package domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    private final Stack<Card> deck;

    public CardDeck() {
        List<Card> cards = Mark.getValues().stream()
                .flatMap(mark -> Letter.getValues().stream()
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
        return new Hand(List.of(this.draw(), this.draw()));
    }

    public Card draw() {
        return deck.pop();
    }
}
