package domain.card;

import static domain.Game.INIT_CARD_COUNT;

import domain.enums.Rank;
import java.util.ArrayList;
import java.util.List;

public class CardBoard {
    private static final int BLACKJACK_CRITERION = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .filter(card -> !card.rank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();

        boolean aceExist = cards.stream()
                .anyMatch(card -> card.rank().equals(Rank.ACE));
        if (aceExist) {
            return (score + calculateAceScore(BLACKJACK_CRITERION - score));
        }
        return score;
    }

    private int calculateAceScore(int remainScore) {
        int minAceScore = cards.stream()
                .filter(card -> card.rank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();

        if (remainScore < minAceScore + ACE_BONUS_SCORE) {
            return minAceScore;
        }
        return minAceScore + ACE_BONUS_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_CRITERION;
    }

    public boolean checkScoreUnderCriterion() {
        return calculateScore() <= BLACKJACK_CRITERION;
    }

    public boolean isBlackjack() {
        if (cards.size() != INIT_CARD_COUNT) {
            return false;
        }
        return calculateScore() == BLACKJACK_CRITERION;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
