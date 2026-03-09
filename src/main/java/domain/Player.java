package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        boolean hasAce = false;

        for (Card card : cards) {
            score += card.getScore();
            if (card.isAce()) {
                hasAce = true;
            }
        }
        return applyAceBonus(score, hasAce);
    }

    private int applyAceBonus(int score, boolean hasAce) {
        if (hasAce && score + 10 <= 21) {
            return score + 10;
        }
        return score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}