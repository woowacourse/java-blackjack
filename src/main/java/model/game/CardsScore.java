package model.game;

import model.card.Card;
import model.card.Cards;

public class CardsScore {

    private static final int ACE_SCORE_HIGH = 11;
    private static final int ACE_SCORE_LOW = 1;
    private static final int MINIMUM_SCORE_FOR_ACE_HIGH = 11;
    private static final int BLACKJACK_SCORE = 21;

    private final Cards cards;
    private final int score;

    private CardsScore(Cards cards, int score) {
        this.cards = cards;
        this.score = score;
    }

    public static CardsScore from(Cards cards) {
        int totalScore = calculateTotalScore(cards);
        return new CardsScore(cards, totalScore);
    }

    private static int calculateTotalScore(Cards cards) {
        int totalScore = cards.calculateTotalNumbers();
        while (totalScore <= MINIMUM_SCORE_FOR_ACE_HIGH && hasAce(cards)) {
            totalScore += ACE_SCORE_HIGH - ACE_SCORE_LOW;
        }
        return totalScore;
    }

    private static boolean hasAce(Cards cards) {
        return cards.getCards()
            .stream()
            .anyMatch(Card::isAce);
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBurst() {
        return score > BLACKJACK_SCORE;
    }

    public Cards getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
