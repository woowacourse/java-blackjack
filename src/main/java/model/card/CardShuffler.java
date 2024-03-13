package model.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class CardShuffler {

    private final Queue<Card> cards;

    public CardShuffler(Queue<Card> cards) {
        this.cards = cards;
    }

    public static CardShuffler of(int dequeCount) {
        List<Card> cards = generateCards(dequeCount);
        Collections.shuffle(cards);
        return new CardShuffler(new ArrayDeque<>(cards));
    }

    private static List<Card> generateCards(int dequeCount) {
        List<Card> cards = new ArrayList<>();
        while (dequeCount > 0) {
            cards.addAll(new Deque().getCards());
            dequeCount--;
        }
        return cards;
    }

    public int cardsSize() {
        return cards.size();
    }
}
