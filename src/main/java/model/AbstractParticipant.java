package model;

import java.util.List;

public abstract class AbstractParticipant implements Participant {
    private static final int BUST_LIMIT = 21;
    private static final int ACE_BONUS_SCORE = 10;
    private final String name;
    private final Cards cards;
    private boolean isBlackJack;

    public AbstractParticipant(String name) {
        this.name = name;
        this.cards = Cards.createEmpty();
        isBlackJack = false;
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
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

    public void blackJack() {
        this.isBlackJack = true;
    }
}
