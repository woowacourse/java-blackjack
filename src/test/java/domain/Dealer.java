package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final List<Card> cards = new ArrayList<>();

    public Dealer() {
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int calculateScore() {
        int score = 0;

        score += cards.stream()
                .filter(card -> !card.getRank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();
        //TODO: A 1개를 11로 처리하기
        return (score + calculateAScore(score));
    }

    private int calculateAScore(int score) {
        int remainScore = 21 - score;
        int minAScore = cards.stream()
                .filter(card -> card.getRank().equals(Rank.ACE))
                .mapToInt(Card::getRankScore)
                .sum();
        if (minAScore == 0) {
            return 0;
        }
        int maxAScore = minAScore + 10;
        if (remainScore < maxAScore) {
            return minAScore;
        }
        return maxAScore;
    }
}
