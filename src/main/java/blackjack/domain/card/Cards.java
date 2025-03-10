package blackjack.domain.card;

import static blackjack.domain.BlackjackConstants.BUST_THRESHOLD;
import static blackjack.domain.BlackjackConstants.DEFAULT_CARD_SIZE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        return cards.size() == DEFAULT_CARD_SIZE.getSymbol()
                && scoreCalculator.calculateMaxScore(cards) == BUST_THRESHOLD.getSymbol();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void take(Card... cards) {
        if (Arrays.stream(cards).count() > DEFAULT_CARD_SIZE.getSymbol()) {
            throw new IllegalArgumentException("카드는 한 번에 최대 두 장까지 받을 수 있습니다.");
        }
        int minScore = scoreCalculator.calculateMaxScore(this.cards);
        if (minScore >= BUST_THRESHOLD.getSymbol()) {
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
