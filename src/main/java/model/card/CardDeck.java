package model.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.stream.Stream;

public class CardDeck {

    private static final int MIN_DECK_COUNT = 1;

    private final Queue<Card> deck;

    public CardDeck(Queue<Card> deck) {
        this.deck = deck;
    }

    public static CardDeck createShuffledDeck(int deckCount) {
        validateDeckCount(deckCount);
        List<Card> deck = generateDeck(deckCount);
        Collections.shuffle(deck);
        return new CardDeck(new ArrayDeque<>(deck));
    }

    private static void validateDeckCount(int deckCount) {
        if (deckCount < MIN_DECK_COUNT) {
            throw new IllegalArgumentException("1개 이상의 덱을 사용해야 합니다.");
        }
    }

    private static List<Card> generateDeck(int deckCount) {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < deckCount; i++) {
            deck.addAll(createCards());
        }
        return deck;
    }

    private static List<Card> createCards() {
        return Arrays.stream(CardNumber.values())
            .flatMap(CardDeck::createCardsWithCardNumber)
            .toList();
    }

    private static Stream<Card> createCardsWithCardNumber(CardNumber cardNumber) {
        return Arrays.stream(CardShape.values())
            .map(cardShape -> new Card(cardNumber, cardShape));
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new NoSuchElementException("더 이상 뽑을 카드가 없습니다.");
        }
        return deck.poll();
    }

    public int size() {
        return deck.size();
    }
}
