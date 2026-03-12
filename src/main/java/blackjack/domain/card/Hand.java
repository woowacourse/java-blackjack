package blackjack.domain.card;

import static blackjack.domain.deck.Deck.INITIAL_CARD_COUNT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    public static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int baseScore = cards.stream()
                .mapToInt((card) -> card.getRank().getBaseScore())
                .sum();

        int aceCount = (int) cards.stream()
                .filter((card) -> card.isAce())
                .count();

        for (int i = 0; i < aceCount; i++) {
            baseScore = convertAceToHigh(baseScore);
        }
        return baseScore;
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_SCORE && cards.size() == INITIAL_CARD_COUNT;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    private int convertAceToHigh(int currentScore) {
        int aceScoreGap = Rank.ACE.getHighScore() - Rank.ACE.getBaseScore();
        if (currentScore + aceScoreGap <= BLACKJACK_SCORE) {
            return currentScore + aceScoreGap;
        }
        return currentScore;
    }

}

