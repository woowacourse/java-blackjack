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

    public int calculateScore() {
        int totalScore = cards.stream().mapToInt(Card::getPoint).sum();
        int aceCount = getAceCount();
        if (aceCount != 0) {
            return calculateScoreWithAce(aceCount, totalScore);
        }
        return totalScore;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    private int getAceCount() {
        return (int)cards.stream().filter(card -> card.isAce()).count();
    }

    private int calculateScoreWithAce(int aceCount, int totalScore) {
        while (aceCount > 0 && totalScore > BUST_THRESHOLD_NUMBER) {
            totalScore = totalScore - ACE_SCORE_DIFFERENCE;
            aceCount--;
        }
        return totalScore;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
