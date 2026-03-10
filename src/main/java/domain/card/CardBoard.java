package domain.card;

import static domain.constant.GameRule.ACE_BONUS_SCORE;
import static domain.constant.GameRule.BLACKJACK_CRITERION;

import domain.enums.Rank;
import java.util.ArrayList;
import java.util.List;

public class CardBoard {
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

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
