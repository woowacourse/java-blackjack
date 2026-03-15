package blackjack.domain;

import blackjack.dto.DrawResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck from(List<Card> cards) {
        return new Deck(cards);
    }

    public static Deck createShuffledDeck() {
        Deck deck = createdDeck();
        return deck.shuffle();
    }

    public static Deck createdDeck() {
        List<Card> deck = new ArrayList<>();
        List<Rank> ranks = Arrays
            .stream(Rank.values())
            .toList();
        List<Suit> suits = Arrays
            .stream(Suit.values())
            .toList();
        for (Rank rank : ranks) {
            matchDenominationWithSymbol(rank, suits, deck);
        }
        return from(deck);
    }

    public Deck shuffle() {
        List<Card> copiedCards = new ArrayList<>(getCards());
        Collections.shuffle(copiedCards);
        return from(copiedCards);
    }

    public DrawResult draw(int count) {
        List<Card> drewCards = new ArrayList<>();
        List<Card> copiedCards = new ArrayList<>(cards);
        for (int i = 0; i < count; i++) {
            drewCards.add(draw(copiedCards));
        }
        return DrawResult.of(drewCards, copiedCards);
    }

    public DrawResult draw() {
        return draw(1);
    }

    private static void matchDenominationWithSymbol(Rank rank, List<Suit> suits, List<Card> deck) {
        for (Suit suit : suits) {
            Card card = new Card(rank, suit);
            deck.add(card);
        }
    }

    private Card draw(List<Card> copiedCards) {
        if (copiedCards.isEmpty()) {
            throw new IllegalArgumentException("뽑을 카드가 남아있지 않습니다.");
        }
        return copiedCards.removeFirst();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
