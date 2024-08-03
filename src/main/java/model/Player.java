package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardsValue() {
        int value = 0;
        int aces = 0;
        for (Card card : cards) {
            value += card.getValue();
            if (card.getRank().equals("A")) {
                aces++;
            }
        }
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }
        return value;
    }

    public boolean wantsCard() {
        return false;
    }
}
