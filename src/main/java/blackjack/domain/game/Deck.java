package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {

    public static final int INIT_CARD_COUNT = 2;

    private final Deque<Card> deck = new ArrayDeque<>();

    public Deck() {
        List<Card> totalCards = new ArrayList<>(Card.getTotalCard());
        Collections.shuffle(totalCards);
        deck.addAll(totalCards);
    }

    public List<Card> pickInit() {
        List<Card> initCards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            initCards.add(pick());
        }
        return initCards;
    }

    public Card pick() {
        return deck.pop();
    }
}
