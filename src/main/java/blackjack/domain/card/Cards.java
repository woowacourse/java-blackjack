package blackjack.domain.card;

import static blackjack.domain.BlackjackConstants.BUST_THRESHOLD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {
    private final List<Card> cards;
    private final ScoreCalculator scoreCalculator;

    public Cards(List<Card> cards, ScoreCalculator scoreCalculator) {
        this.cards = cards;
        this.scoreCalculator = scoreCalculator;
    }

    public int calculateMaxScore() {
        return scoreCalculator.calculateMaxScore(cards);
    }

    public int calculateMinScore() {
        return scoreCalculator.calculateMinScore(cards);
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

    public void take(Card... cards) {
        if (isBust()) {
            throw new IllegalArgumentException("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
        }
        this.cards.addAll(Arrays.asList(cards));
    }

    public boolean isBust() {
        return scoreCalculator.calculateMinScore(cards) > BUST_THRESHOLD.getSymbol();
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Cards cards1 = (Cards) object;
        return Objects.equals(getCards(), cards1.getCards()) && Objects.equals(scoreCalculator,
                cards1.scoreCalculator);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getCards());
        result = 31 * result + Objects.hashCode(scoreCalculator);
        return result;
    }
}
