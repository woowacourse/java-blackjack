package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    public final Stack<Card> cards;

    public Deck() {
        this.cards = generateCards();
    }

    private Stack<Card> generateCards() {
        Stack<Card> cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            addCardBySuit(cards, suit);
        }
        shuffle(cards);
        return cards;
    }

    private void addCardBySuit(List<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
