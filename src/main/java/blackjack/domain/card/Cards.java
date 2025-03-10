package blackjack.domain.card;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final int DEFAULT_CARD_SIZE = 2;
    public static final int BUST_THRESHOLD = 21;
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
        return cards.size() == DEFAULT_CARD_SIZE
                && scoreCalculator.calculateMaxScore(cards) == BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void take(Card... cards) {
        if (Arrays.stream(cards).count() > DEFAULT_CARD_SIZE) {
            throw new IllegalArgumentException("카드는 한 번에 최대 두 장까지 받을 수 있습니다.");
        }
        int minScore = scoreCalculator.calculateMaxScore(this.cards);
        if (minScore >= BUST_THRESHOLD) {
            throw new IllegalArgumentException("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
        }
        this.cards.addAll(Arrays.asList(cards));
    }

    public int getSize() {
        return cards.size();
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
