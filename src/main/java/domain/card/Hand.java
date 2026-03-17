package domain.card;

import static constant.GameRule.ACE_BONUS_SCORE;
import static constant.GameRule.BLACKJACK_CRITERION;

import domain.enums.Rank;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(Card::rankScore)
                .sum();

        boolean aceExist = cards.stream()
                .anyMatch(card -> card.rank().equals(Rank.ACE));

        if (aceExist && (totalScore + ACE_BONUS_SCORE) <= BLACKJACK_CRITERION) {
            return totalScore + ACE_BONUS_SCORE;
        }

        return totalScore;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_CRITERION;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getSize() {
        return cards.size();
    }
}
