package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private final List<Card> cards;
    private final Queue<Card> cardQueue;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.cardQueue = new LinkedList<>(cards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public List<Card> getCardQueue() {
        return new ArrayList<>(cardQueue);
    }

    public Card draw() {
        return cardQueue.poll();
    }
}
