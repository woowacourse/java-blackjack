package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;

public class ParticipantCards {
    private static final String DELIMITER = ", ";
    private static final int ACE_SCORE = 1;
    private static final int MAX_SCORE = 21;
    private static final int ACE_BONUS = 10;
    private static final int FIRST_CARD_INDEX = 0;
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
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

    public int calculateScore() {
        int score = 0;
        boolean containAce = false;
        for (Card card : cards) {
            if (card.getScore() == ACE_SCORE) {
                containAce = true;
            }
            score += card.getScore();
        }
        return calculateFinalScore(score, containAce);
    }

    private int calculateFinalScore(int score, boolean containAce) {
        if (containAce && score + ACE_BONUS <= MAX_SCORE) {
            return score + ACE_BONUS;
        }
        return score;
    }
}
