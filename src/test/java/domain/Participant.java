package domain;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .filter(card -> !card.getRank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();

        boolean aExist = cards.stream()
                .anyMatch(card -> card.getRank().equals(Rank.ACE));

        if (aExist) {
            return (score + calculateAScore(21 - score));
        }

        return score;
    }

    private int calculateAScore(int remainScore) {
        int minAScore = cards.stream()
                .filter(card -> card.getRank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();

        if (remainScore < minAScore + 10) {
            return minAScore;
        }

        return minAScore + 10;
    }

    public boolean isBurst() {
        return calculateScore() > 21;
    }
}
