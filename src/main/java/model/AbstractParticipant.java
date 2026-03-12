package model;

import java.util.List;

public abstract class AbstractParticipant implements Participant {
    private static final int BUST_LIMIT = 21;
    private static final int BLACK_JACK = 21;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACK_JACK_REQUIREMENT = 2;
    private final String name;
    private final Cards cards;
    private boolean isBlackJack;
    private long profit;

    public AbstractParticipant(String name) {
        this.name = name;
        this.cards = Cards.createEmpty();
        isBlackJack = false;
        profit = 0;
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
    public void addCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() < BUST_LIMIT;
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
    public void checkBlackJack() {
        if (calculateTotalScore() == BLACK_JACK && this.cards.size() == BLACK_JACK_REQUIREMENT) {
            this.isBlackJack = true;
        }
    }

    public boolean isBlackJack() {
        return this.isBlackJack;
    }

    public long profit() {
        return this.profit;
    }

    public void addProfit(long money) {
        this.profit += money;
    }

    public void subtractProfit(long money) {
        this.profit -= money;
    }
}
