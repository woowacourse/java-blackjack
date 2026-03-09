package model;

import java.util.List;

public class Player {

    private static final int BUST_LIMIT = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final String name;
    private final Cards cards;

    public Player(String name) {
        this.name = name;
        this.cards = Cards.createEmpty();
    }

    public int calculateTotalScore() {
        int score = this.cards.calculateScore();
        int aceCount = this.cards.countAce();

        while (aceCount > 0 && score + ACE_BONUS_SCORE <= BUST_LIMIT) {
            score += ACE_BONUS_SCORE;
            aceCount--;
        }
        return score;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public boolean canHit() {
        return calculateTotalScore() < BUST_LIMIT;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCard();
    }
}
