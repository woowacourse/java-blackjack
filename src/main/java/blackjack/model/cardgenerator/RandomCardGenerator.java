package blackjack.model.cardgenerator;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;

import java.util.*;

public class RandomCardGenerator implements CardGenerator {
    private static final String NO_CARDS = "카드가 없습니다.";
    private static final int DECK_NUMBER = 4;

    private final Queue<Card> decks;

    public RandomCardGenerator() {
        this.decks = generateDecks();
    }

    private Queue<Card> generateDecks() {
        List<Card> deck = generateDeck();

        List<Card> decks = new ArrayList<>();
        while (decks.size() < deck.size() * DECK_NUMBER) {
            decks.addAll(deck);
        }
        return shuffle(decks);
    }

    private List<Card> generateDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            generateCardsWithSuit(deck, suit);
        }
        return deck;
    }

    private void generateCardsWithSuit(final List<Card> deck, final Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            deck.add(new Card(suit, denomination));
        }
    }

    private Queue<Card> shuffle(final List<Card> decks) {
        Collections.shuffle(decks);
        return new ArrayDeque<>(decks);
    }

    @Override
    public Card pick() {
        if (!decks.isEmpty()) {
            return decks.poll();
        }
        throw new NoSuchElementException(NO_CARDS);
    }
}
