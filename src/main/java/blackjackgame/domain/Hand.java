package blackjackgame.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int MAX_BASIC_SCORE = 11;
    private static final int ACE_BONUS_SCORE = 10;
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
}
