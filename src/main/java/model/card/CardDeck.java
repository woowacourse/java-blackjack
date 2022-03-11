package model.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {
    private final Queue<Card> deck;

    public CardDeck() {
        this.deck = new LinkedList<>(initCardDeck());
    }

    private List<Card> initCardDeck() {
        List<Card> cards = Card.getAllCards();
        Collections.shuffle(cards);
        return cards;
    }

    public Card drawCard() {
        checkIsEmpty();
        return deck.poll();
    }

    private void checkIsEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("카드를 모두 소진했습니다!!");
        }
    }
}
