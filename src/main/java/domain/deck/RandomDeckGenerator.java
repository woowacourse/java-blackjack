package domain.deck;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import java.util.Collections;
import java.util.Stack;

public class RandomDeckGenerator implements DeckGenerator {
    private static void addCard(Stack<Card> cards, Suits suits) {
        for (Denomination denomination : Denomination.values()) {
            cards.push(new Card(denomination, suits));
        }
    }

    @Override
    public Deck generateDeck() {
        Stack<Card> cards = new Stack<>();
        for (Suits suits : Suits.values()) {
            addCard(cards, suits);
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
