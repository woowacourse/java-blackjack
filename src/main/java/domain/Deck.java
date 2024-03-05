package domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {

    private static final Random RANDOM  = new Random();
    private final List<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card pickRandomCard() {
        int cardIndex = RANDOM.nextInt(cards.size());
        return cards.remove(cardIndex);
    }

    public int calculateTotalScore() {
        if (cards.isEmpty()) {
            return 0;
        }
        return cards.stream()
                .map(Card::getRank)
                .mapToInt(Rank::getScore)
                .sum();
    }
}
