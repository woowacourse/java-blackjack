package domain.card;

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

    // FIXME: 카드의 인덱스 접근이 의미상 부자연스러움
    public void removeCardOf(final int index) {
        deck.remove(index);
    }

    public List<Card> getDeck() {
        return List.copyOf(deck);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Card getCardOf(final int index) {
        return deck.get(index);
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
