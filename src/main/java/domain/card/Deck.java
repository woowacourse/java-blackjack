package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
    private static final int ADDITIONAL_DRAW_CARD_AMOUNT = 1;

    private Queue<Card> deck;

    public Deck(List<Card> deck) {
        Collections.shuffle(deck);
        this.deck = new LinkedList<>(deck);
    }

    public List<Card> popCard(int number) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            cards.add(deck.poll());
        }
        return cards;
    }

    public List<Card> popCard() {
        return popCard(ADDITIONAL_DRAW_CARD_AMOUNT);
    }
}
