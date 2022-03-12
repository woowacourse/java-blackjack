package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class CardDeck {

    public static final int INIT_CARD_COUNT = 2;

    private final Deque<Card> cardDeck = new ArrayDeque<>();

    public CardDeck() {
        List<Card> totalCards = new ArrayList<>(Card.getTotalCard());
        Collections.shuffle(totalCards);
        cardDeck.addAll(totalCards);
    }

    public List<Card> pickInit() {
        List<Card> initCards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            initCards.add(pick());
        }
        return initCards;
    }

    public Card pick() {
        return cardDeck.pop();
    }
}
