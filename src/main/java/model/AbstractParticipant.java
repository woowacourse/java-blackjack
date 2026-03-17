package model;

import java.util.List;

public abstract class AbstractParticipant implements Participant {
    private static final int BUST_LIMIT = 21;
    private static final int BLACK_JACK = 21;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACK_JACK_REQUIREMENT = 2;

    private final String name;
    private final Cards cards;
    private long profit;

    public AbstractParticipant(String name) {
        this.name = name;
        this.cards = Cards.createEmpty();
        this.profit = 0;
    }

    @Override
    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addProfit(long money) {
        this.profit += money;
    }

    public void subtractProfit(long money) {
        this.profit -= money;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<Card> cards() {
        return cards.cards();
    }

    @Override
    public int calculateTotalScore() {
        int score = this.cards.calculateScore();
        int aceCount = this.cards.countAce();

        while (aceCount > 0 && score + ACE_BONUS_SCORE <= BUST_LIMIT) {
            score += ACE_BONUS_SCORE;
            aceCount--;
        }
        return score;
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() < BUST_LIMIT;
    }

    public boolean isBlackJack() {
        if (calculateTotalScore() == BLACK_JACK && this.cards.size() == BLACK_JACK_REQUIREMENT) {
            return true;
        }
        return false;
    }

    public long profit() {
        return this.profit;
    }
}
