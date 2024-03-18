package blackjack.model.deck;

import blackjack.model.participant.Hand;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    private final Deque<Card> deck;

    public Deck(final Deque<Card> deck) {
        this.deck = deck;
    }

    public static Deck createByRandomOrder() {
        return new Deck(makeRandomOrderDeck());
    }

    private static Deque<Card> makeRandomOrderDeck() {
        List<Card> cards = Card.getCards();
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    public Hand distributeInitialCard() {
        return new Hand(List.of(distribute(), distribute()));
    }

    public Card distribute() {
        try {
            return deck.removeFirst();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("카드가 부족합니다.");
        }
    }

    public Deque<Card> getDeck() {
        return deck;
    }
}
