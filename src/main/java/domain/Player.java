package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private final List<Card> cards = new ArrayList<>();

    public Player(final String name) {
        this.name = name;
    }

    public Player() {
    }

    public void addCard(final Card card) {
        cards.add(card);
    }


    public boolean alive() {
        return calculateScore() < 21;
    }

    public int calculateScore() {
        int score = 0;
        for (final Card card : cards) {
            score += card.getValue(score);
        }
        return score;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
