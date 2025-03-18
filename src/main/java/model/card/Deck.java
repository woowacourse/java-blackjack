package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> deckCards;

    public static Deck of() {
        List<Card> deck = new ArrayList<>();
        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                Card card = new Card(rank, cardSuit);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    private Deck(List<Card> deckCards) {
        this.deckCards = deckCards;
    }

    public Card pick() {
        validateDeckEmpty();
        Card card = deckCards.getFirst();
        deckCards.remove(card);
        return card;
    }

    private void validateDeckEmpty() {
        if (deckCards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 주어진 모든 카드들을 소진하였습니다");
        }
    }
}
