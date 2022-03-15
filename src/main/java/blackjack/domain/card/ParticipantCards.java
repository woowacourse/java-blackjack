package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantCards {

    private static final int INITIAL_CARD_SIZE = 2;
    private static final int BLACKJACK_THRESHOLD_NUMBER = 21;
    private static final int ACE_SCORE_DIFFERENCE = 10;

    private final List<Card> cards;

    public ParticipantCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_CARD_SIZE && getScore() == BLACKJACK_THRESHOLD_NUMBER;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_THRESHOLD_NUMBER;
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getPoint();
        }
        return calculateScoreAdvantageousWithAce(score);
    }

    private int calculateScoreAdvantageousWithAce(int score) {
        if (isExistAce() && score + ACE_SCORE_DIFFERENCE <= BLACKJACK_THRESHOLD_NUMBER) {
            score += ACE_SCORE_DIFFERENCE;
        }
        return score;
    }

    private boolean isExistAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
