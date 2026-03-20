package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BUST_CRITERIA = 21;
    private static final int ACE_EXTRA_SCORE = 10;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateCardScoreSum() {
        int scoreSum = getScoreSumWithBasicAceScore();
        if (hasAce() && canApplyMaxAceScore(scoreSum)) {
            scoreSum += ACE_EXTRA_SCORE;
        }
        return scoreSum;
    }

    private int getScoreSumWithBasicAceScore() {
        return cards.stream().mapToInt(Card::getCardScore).sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private boolean canApplyMaxAceScore(int scoreSum) {
        return scoreSum + ACE_EXTRA_SCORE <= BUST_CRITERIA;
    }

    public boolean isLessThanMaxScore() {
        return calculateCardScoreSum() < BUST_CRITERIA;
    }

    public boolean isBust() {
        return calculateCardScoreSum() > BUST_CRITERIA;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARD_SIZE && calculateCardScoreSum() == BUST_CRITERIA;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
