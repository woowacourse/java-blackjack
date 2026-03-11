package domain.card;

import static exception.ErrorMessage.DECK_EMPTY;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// TODO: draw() 책임을 갖도록 리팩터링
public class Deck {
    private final Deque<Card> deck;

    public Deck() {
        this(new DefaultShuffler());
    }

    public Deck(final Shuffler shuffler) {
        final List<Card> orderedCards = initCards();
        shuffler.shuffle(orderedCards);
        this.deck = new ArrayDeque<>(orderedCards);
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IllegalStateException(DECK_EMPTY.getMessage());
        }

        return deck.pollFirst();
    }

    public List<Card> getDeck() {
        return List.copyOf(deck);
    }


    private static List<Card> initCards() {
        final List<Card> cards = new ArrayList<>();
        for (final CardSuit suit : CardSuit.values()) {
            addCard(suit, cards);
        }
        return cards;
    }

    private static void addCard(final CardSuit suit, final List<Card> cards) {
        for (final CardRank rank : CardRank.values()) {
            cards.add(new Card(suit, rank));
        }
    }
}
