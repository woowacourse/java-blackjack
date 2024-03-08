package domain.card;

import static domain.card.CardNumber.ACE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Cards {

    private final Stack<Card> cards;

    public Cards() {
        this.cards = new Stack<>();
    }

    public Cards(final Collection<Card> cards) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Card draw() {
        return cards.pop();
    }

    public Card peek() {
        return cards.peek();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::value)
                .sum();
    }

    public boolean hasAce() {
        return cards.stream().anyMatch(card -> card.number() == ACE);
    }

    public List<Card> toList() {
        return List.copyOf(cards);
    }
}
