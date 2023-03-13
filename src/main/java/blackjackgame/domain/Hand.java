package blackjackgame.domain;

import java.util.ArrayList;
import java.util.List;

import static blackjackgame.domain.GameOutcome.*;

public class Hand {
    private static final int MAX_BASIC_SCORE = 11;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int BLACKJACK_MAX_SCORE = 21;
    private static final int BLACKJACK_DECK_SIZE = 2;
    private final List<Card> cards;

    public Hand(final Card firstCard, final Card secondCard) {
        this.cards = new ArrayList<>(List.of(firstCard, secondCard));
    }

    public int getScore() {
        int totalScore = getBasicScore();
        boolean hasAce = findAce();

        if (hasAce && totalScore <= MAX_BASIC_SCORE) {
            totalScore += ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    private boolean findAce() {
        return cards.stream()
                .anyMatch(card -> card.getValue().equals(CardValue.ACE.getValue()));
    }

    private int getBasicScore() {
        int basicScore = 0;
        for (final Card card : cards) {
            basicScore += card.getScore();
        }
        return basicScore;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public GameOutcome calculateOutcomeComparedBy(final Hand otherHand) {
        if (isBlackJack(this)) {
            return judgeResultWhenBlackJack(otherHand);
        }
        if (getScore() <= BLACKJACK_MAX_SCORE) {
            return judgeResultWhenUnderMaxScore(otherHand);
        }
        return LOSE;
    }

    private boolean isBlackJack(final Hand hand) {
        return hand.getScore() == BLACKJACK_MAX_SCORE && hand.getCards().size() == BLACKJACK_DECK_SIZE;
    }

    private GameOutcome judgeResultWhenBlackJack(final Hand otherHand) {
        if (otherHand.getScore() == BLACKJACK_MAX_SCORE && otherHand.getCards().size() == BLACKJACK_DECK_SIZE) {
            return DRAW;
        }
        return BLACKJACK_WIN;
    }

    private GameOutcome judgeResultWhenUnderMaxScore(final Hand otherHand) {
        int otherScore = otherHand.getScore();
        int myScore = getScore();
        if (isBlackJack(otherHand) || otherScore <= BLACKJACK_MAX_SCORE && otherScore > myScore) {
            return LOSE;
        }
        if (otherScore > BLACKJACK_MAX_SCORE || otherScore < myScore) {
            return WIN;
        }
        return DRAW;
    }
}
