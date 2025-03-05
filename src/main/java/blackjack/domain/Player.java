package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards;
    private final ScoreCalculator scoreCalculator;

    public Player(String name, List<Card> cards, ScoreCalculator scoreCalculator) {
        this.name = name.trim();
        this.cards = cards;
        this.scoreCalculator = scoreCalculator;
    }

    public void send(Card... cards) {
        int minScore = scoreCalculator.calculateMaxScore(this.cards);
        if (minScore >= 21) {
            throw new IllegalArgumentException("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
        }
        this.cards.addAll(Arrays.asList(cards));
    }

    public int calculateMaxScore() {
        return scoreCalculator.calculateMaxScore(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getName() {
        return name;
    }
}
