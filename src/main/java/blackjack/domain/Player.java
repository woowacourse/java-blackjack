package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public boolean isBlackjack() {
        if (cards.size() != 2) {
            return false;
        }
        Set<Rank> ranks = cards.stream()
                .map(Card::getRank)
                .collect(Collectors.toSet());
        if (!ranks.contains(Rank.ACE)) {
            return false;
        }

        return ranks.contains(Rank.KING) ||
                ranks.contains(Rank.QUEEN) ||
                ranks.contains(Rank.JACK) ||
                ranks.contains(Rank.TEN);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public String getName() {
        return name;
    }

    public boolean canSend() {
        int minScore = scoreCalculator.calculateMinScore(cards);
        return minScore < 21;
    }
}
