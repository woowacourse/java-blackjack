package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {

    private final static int BLACKJACK_MAX_CARD_SIZE = 2;

    private final List<Card> cards;
    private final ScoreCalculator scoreCalculator;

    public Cards(Card... cards) {
        this(new ArrayList<>(List.of(cards)), new ScoreCalculator());
    }

    public Cards(List<Card> cards) {
        this(cards, new ScoreCalculator());
    }

    public Cards(List<Card> cards, ScoreCalculator scoreCalculator) {
        this.cards = cards;
        this.scoreCalculator = scoreCalculator;
    }

    public Score calculateMaxScore() {
        return scoreCalculator.calculateMaxScore(cards);
    }

    public boolean isBlackjack() {
        if (cards.size() != BLACKJACK_MAX_CARD_SIZE) {
            return false;
        }
        return calculateMaxScore().isBlackjack();
    }

    public void take(Card... cards) {
        if (isBust()) {
            throw new IllegalStateException("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
        }
        this.cards.addAll(Arrays.asList(cards));
    }

    public boolean isBust() {
        return scoreCalculator.calculateMinScore(cards).isBust();
    }

    public int size() {
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
        return Objects.equals(getCards(), cards1.getCards());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCards());
    }
}
