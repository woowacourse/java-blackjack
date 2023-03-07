package domain;

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
        return new Score(cards.stream()
                .map(Card::score)
                .mapToInt(Score::getValue)
                .sum());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
