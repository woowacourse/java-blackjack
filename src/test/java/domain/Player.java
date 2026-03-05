package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
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

    public boolean isBurst() {
        return calculateScore() > 21;
    }
}
