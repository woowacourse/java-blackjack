package domain.deck;

import domain.Card;
import domain.Denomination;
import domain.Suits;
import java.util.Collections;
import java.util.Stack;

public class DefaultDeckGenerator implements DeckGenerator {
    @Override
    public Deck generateDeck() {
        Stack<Card> cards = new Stack<>();
        for (Suits suits : Suits.values()) {
            addCard(cards, suits);
        }
        Collections.reverse(cards);
        return new Deck(cards);
    }

    private static void addCard(Stack<Card> cards, Suits suits) {
        for (Denomination denomination : Denomination.values()) {
            cards.push(new Card(denomination, suits));
        }
    }
}
