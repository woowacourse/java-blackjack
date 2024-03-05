package player;

import card.Card;
import card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> cards;

    public Player() {
        this.cards = new ArrayList<>();
    }

    public void drawCard(Deck deck) {
        cards.add(deck.draw());
    }

    public int calculateScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);
    }
}