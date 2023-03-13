package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public final class ShuffledDeck implements Deck {
    private static final List<Integer> AVAILABLE_DECK_COUNT = List.of(1, 2, 4, 6, 8);

    private final Deque<Card> deck;

    public ShuffledDeck(Deque<Card> deck) {
        this.deck = deck;
    }

    public static ShuffledDeck createByCount(int deckCount) {
        validateDeckCount(deckCount);
        List<Card> cards = generateShuffledCardsByDeckCount(deckCount);
        Deque<Card> deck = new ArrayDeque<>(cards);
        return new ShuffledDeck(deck);
    }

    private static List<Card> generateShuffledCardsByDeckCount(int deckCount) {
        List<Card> cards = new ArrayList<>();
        for (int count = 0; count < deckCount; count++) {
            addCardDummyTo(cards);
        }
        Collections.shuffle(cards);
        return cards;
    }

    private static void addCardDummyTo(List<Card> cards) {
        for (Suit suit : Suit.values()) {
            addCardsWithSymbolOf(suit, cards);
        }
    }

    private static void addCardsWithSymbolOf(Suit suit, List<Card> cards){
        for (Rank rank : Rank.values()){
            cards.add(new Card(suit, rank));
        }
    }

    private static void validateDeckCount(int deckCount) {
        if (AVAILABLE_DECK_COUNT.contains(deckCount)) {
            return;
        }
        throw new IllegalArgumentException(String.format(
                "%s개의 덱만 사용 가능합니다.", AVAILABLE_DECK_COUNT.toString()));
    }

    @Override
    public Card draw() {
        try {
            return this.deck.pop();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("덱이 비었습니다.");
        }
    }
}
