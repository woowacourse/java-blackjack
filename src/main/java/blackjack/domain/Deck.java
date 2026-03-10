package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = createShuffledDeck();
    }

    public static List<Card> createShuffledDeck() {
        List<Card> deck = createdDeck();
        return shuffle(deck);
    }

    public static List<Card> createdDeck() {
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
        return deck;
    }

    private static void matchDenominationWithSymbol(Rank rank, List<Suit> suits, List<Card> deck) {
        for (Suit suit : suits) {
            Card card = new Card(rank, suit);
            deck.add(card);
        }
    }

    public static List<Card> shuffle(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    public List<Card> drawCard() {
        return drawCards(1);
    }

    public List<Card> drawCards(int count) {
        hasEnoughCard(count);

        List<Card> drewCards = new ArrayList<>(cards.subList(0, count));
        cards.subList(0, count).clear();

        return List.copyOf(drewCards);
    }

    private void hasEnoughCard(int count) {
        if (cards.size() < count) {
            throw new IllegalArgumentException("남은 카드가 뽑을 카드보다 적습니다.");
        }
    }
}
