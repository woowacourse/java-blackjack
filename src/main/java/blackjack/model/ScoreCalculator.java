package blackjack.model;

import java.util.List;

public class ScoreCalculator {

    public int getCardScore(List<Card> cards) {
        long aceCount = cards.stream()
                .filter(card -> Number.ACE.equals(card.getNumber()))
                .count();

        int score = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        if (aceCount != 0) {
            return aceTranslate(score, aceCount);
        }
        return score;
    }

    public int getReceivedOneCardScore(Participant participant) {
        int updatedScore = getCardScore(participant.getCards());
        return updatedScore - participant.getScore();
    }

    private int aceTranslate(int score, long aceCount) {
        while (score <= 11 && aceCount > 0) {
            score += 10;
            aceCount--;
        }
        return score;
    }
}
