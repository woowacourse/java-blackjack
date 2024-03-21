package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.card.CardScore.ACE_ADD_SCORE;

public class Hand {
    public static final int DEFAULT_DRAW_COUNT = 1;
    private static final int MAX_SCORE = 21;
    private final Deck deck;
    private final List<Card> cards;

    public Hand(Deck deck) {
        this.deck = deck;
        this.cards = new ArrayList<>();
    }

    public void draw() {
        draw(DEFAULT_DRAW_COUNT);
    }

    public void draw(int count) {
        final List<Card> drawCards = deck.draw(count);
        cards.addAll(drawCards);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int size() {
        return cards.size();
    }

    public boolean isBlackjackScore() {
        return totalScore() == MAX_SCORE;
    }

    public boolean isBustScore() {
        return totalScore() > MAX_SCORE;
    }

    public boolean isUnderBoundScore() {
        return totalScore() < MAX_SCORE;
    }

    public int totalScore() {
        if (containAce()) {
            return calculateBiggestNotOverMax(sumScores(), sumScores() + ACE_ADD_SCORE);
        }
        return sumScores();
    }

    private boolean containAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateBiggestNotOverMax(int score1, int score2) {
        if (score1 > MAX_SCORE) {
            return score2;
        }
        if (score2 > MAX_SCORE) {
            return score1;
        }
        return Math.max(score1, score2);
    }

    private int sumScores() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}
