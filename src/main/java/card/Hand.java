package card;

import game.BlackJackGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ADDITIONAL_ACE_SCORE = 10;

    private final List<Card> cards;

    public Hand() {
        this(new ArrayList<>());
    }

    Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        int maximumScore = calculateMaximumScore();
        int minimumScore = calculateMinimumScore();

        if (maximumScore > BlackJackGame.BLACKJACK_MAX_SCORE) {
            return minimumScore;
        }
        return maximumScore;
    }

    private int calculateMinimumScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);
    }

    private int calculateMaximumScore() {
        int score = calculateMinimumScore();
        boolean isAce = cards.stream().anyMatch(Card::isAce);

        if (isAce) {
            return score + ADDITIONAL_ACE_SCORE;
        }
        return score;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
