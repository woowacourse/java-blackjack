package blackjack.player;

import blackjack.card.Card;
import blackjack.game.BlackJackGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int ADDITIONAL_ACE_SCORE = 10;

    private final List<Card> cards;

    Hand(List<Card> cards) {
        this.cards = cards;
    }

    Hand() {
        this(new ArrayList<>());
    }

    public int calculateScore() {
        int minimumScore = calculateMinimumScore();
        if (!hasAce()) {
            return minimumScore;
        }
        return changeToMaximumScore(minimumScore);
    }

    private int calculateMinimumScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(0, Integer::sum);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int changeToMaximumScore(int minimumScore) {
        int maximumScore = minimumScore + ADDITIONAL_ACE_SCORE;
        if (maximumScore <= BlackJackGame.BLACKJACK_MAX_SCORE) {
            return maximumScore;
        }
        return minimumScore;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
