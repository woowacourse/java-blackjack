package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantCards {

    private static final int BUST_THRESHOLD_NUMBER = 21;
    private static final int ACE_SCORE_DIFFERENCE = 10;

    private final List<Card> cards;

    public ParticipantCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean isBlackjack() {
        return cards.get(0).getPoint() + cards.get(1).getPoint() == 21;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getPoint();
        }
        score = calculateScoreAdvantageousWithAce(score);
        return score;
    }

    private int calculateScoreAdvantageousWithAce(int score) {
        int aceCount = getAceCount();
        while (aceCount-- > 0 && score > BUST_THRESHOLD_NUMBER) {
            score -= ACE_SCORE_DIFFERENCE;
        }
        return score;
    }

    private int getAceCount() {
        return (int)cards.stream()
            .filter(card -> card.getDenomination() == Denomination.ACE)
            .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
