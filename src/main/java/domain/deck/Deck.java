package domain.deck;

import java.util.*;

public final class Deck {

    private Deque<Card> cards;

    public Deck() {
        cards = initCards();
    }

    private Deque<Card> initCards() {
        Deque<Card> cards = new ArrayDeque<>();
        for (Suit suit : Suit.values()) {
            pushCards(cards, suit);
        }

        return cards;
    }

    private void pushCards(final Deque<Card> cards, final Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.push(Card.of(suit, rank));
        }
    }

    public void shuffleDeck() {
        List<Card> cards = new ArrayList<>(this.cards);
        Collections.shuffle(cards);
        this.cards = new ArrayDeque<>(cards);
    }

    public Card popCard() {
        return cards.pop();
    }
}
