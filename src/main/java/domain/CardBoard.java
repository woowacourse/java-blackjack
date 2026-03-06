package domain;

import domain.enums.Rank;
import java.util.ArrayList;
import java.util.List;

public class CardBoard {
    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateScore() {

        int score = cards.stream()
                .filter(card -> !card.getRank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();

        boolean aceExist = cards.stream()
                .anyMatch(card -> card.getRank().equals(Rank.ACE));

        if (aceExist) {
            return (score + calculateAceScore(21 - score));
        }

        return score;
    }

    private int calculateAceScore(int remainScore) {
        int minAceScore = cards.stream()
                .filter(card -> card.getRank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();

        if (remainScore < minAceScore + 10) {
            return minAceScore;
        }

        return minAceScore + 10;
    }

    public boolean isBurst() {
        return calculateScore() > 21;
    }
}
