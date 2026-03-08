package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPattern;
import domain.exception.DeckIsEmptyException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> queue;

    public Deck() {
        queue = init();
    }

    public LinkedList<Card> init() {
        List<Card> cards = new LinkedList<>();
        for (CardPattern cardPattern : CardPattern.values()) {
            makeCard(cards, cardPattern.getName());
        }
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }

    public Card draw() {
        if (queue.isEmpty()) {
            throw new DeckIsEmptyException();
        }
        return queue.poll();
    }

    private void makeCard(List<Card> cards, String cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardNumber.getCourt(), cardPattern));
        }
    }
}
