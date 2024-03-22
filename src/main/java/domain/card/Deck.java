package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int DEFAULT_NUMBER_OF_PACKS = 6;

    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck create() {
        List<Card> deck = new ArrayList<>();
        for (int i = 0; i < DEFAULT_NUMBER_OF_PACKS; i++) {
            deck.addAll(cardPack());
        }
        return new Deck(deck);
    }

    private static List<Card> cardPack() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            addCardsOf(cards, suit);
        }
        return cards;
    }

    private static void addCardsOf(List<Card> cards, Suit suit) {
        for (Rank rank : Rank.values()) {
            cards.add(new Card(suit, rank));
        }
    }

    public Card draw() {
        validateNotEmpty();
        return cards.remove(0);
    }

    public void shuffle() {
        validateNotEmpty();
        Collections.shuffle(cards);
    }

    private void validateNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다. 카드를 추가하세요.");
        }
    }
}
