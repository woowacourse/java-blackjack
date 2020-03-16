package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class ParticipantCards {
    private static final String DELIMITER = ", ";
    private static final int MAX_SCORE = 21;
    private static final int ACE_BONUS = 10;
    private static final int FIRST_CARD_INDEX = 0;
    private static final int ACE_SCORE = 1;
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = 0;
        boolean containsAce = false;
        for (Card card : cards) {
            containsAce = checkIfAce(card);
            score += card.getScore();
        }
        return calculateFinalScore(score, containsAce);
    }

    private boolean checkIfAce(Card card) {
        return card.getScore() == ACE_SCORE;
    }

    private int calculateFinalScore(int score, boolean containAce) {
        if (containAce && score + ACE_BONUS <= MAX_SCORE) {
            return score + ACE_BONUS;
        }
        return score;
    }

    public String toStringOneCard() {
        return cards.get(FIRST_CARD_INDEX).toString();
    }

    @Override
    public String toString() {
        List<String> cardNames = cards.stream()
            .map(Card::toString)
            .collect(Collectors.toList());
        return String.join(DELIMITER, cardNames);
    }
}
