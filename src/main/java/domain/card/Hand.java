package domain.card;

import domain.Score;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Score score() {
        return calculateScore();
    }

    private Score calculateScore() {
        var countAce = countAce();
        Score score = sum();

        for (int i = 0; i < countAce; i++) {
            score = score.addScoreByAce();
        }

        return score;
    }

    private long countAce() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private Score sum() {
        return cards.stream()
                .map(Card::score)
                .reduce(Score.zero(), Score::sum);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public int size() {
        return cards.size();
    }
}
