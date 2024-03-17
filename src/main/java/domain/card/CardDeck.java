package domain.card;

import constants.ErrorCode;
import exception.NoMoreCardException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private final Deque<Card> cards;

    public CardDeck(final Deque<Card> cards) {
        this.cards = cards;
    }

    static CardDeck generate(int size) {
        final List<Card> deck = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            deck.addAll(Card.values());
        }
        Collections.shuffle(deck);
        return new CardDeck(new ArrayDeque<>(deck));
    }

    public Card pop() {
        if (cards.isEmpty()) {
            throw new NoMoreCardException(ErrorCode.EMPTY_CARD);
        }
        return cards.pop();
    }

    public int size() {
        return cards.size();
    }
}
