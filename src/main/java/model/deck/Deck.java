package model.deck;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import model.participant.role.Gameable;

public final class Deck {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    private final List<Card> deck;

    public static Deck of() {
        List<Card> deck = new LinkedList<>();
        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                Card card = new Card(rank, cardSuit);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    public Deck(final List<Card> deck) {
        this.deck = deck;
    }

    public Card pick() {
        validateDeckEmpty();
        return deck.removeFirst();
    }

    public void dealInitiallyTo(Gameable gameable) {
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> dealTo(gameable)
        );
    }

    public void dealTo(Gameable gameable) {
        gameable.receiveCard(pick());
    }

    private void validateDeckEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("주어진 모든 카드들을 소진하였습니다");
        }
    }
}
