package blakjack.domain.card;

import java.util.*;

public final class CardDeck {
    private static final String EMPTY_MESSAGE = "카드가 모두 소진됐습니다.";

    private final Queue<Card> deck = new LinkedList<>();

    public CardDeck() {
        final List<Card> cards = new ArrayList<>();

        for (final Suit suit : Suit.values()) {
            addCard(cards, suit);
        }

        Collections.shuffle(cards);
        deck.addAll(cards);
    }

    private void addCard(final List<Card> cards, final Suit suit) {
        for (final Face face : Face.values()) {
            cards.add(new Card(suit, face));
        }
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new NoSuchElementException(EMPTY_MESSAGE);
        }
        return deck.poll();
    }
}
