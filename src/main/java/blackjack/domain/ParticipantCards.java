package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantCards {

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

    private int getAceCount() {
        return (int) cards.stream().filter(card -> card.isAce()).count();
    }

    private int calculateScoreWithAce(int aceCount, int totalScore) {
        while (aceCount > 0 && totalScore > 21) {
            totalScore = totalScore - 10;
            aceCount--;
        }
        return totalScore;
    }


    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
