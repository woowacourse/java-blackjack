package blackjack.domain.card;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final int BUST_THRESHOLD = 21;
    private final List<Card> cards;
    private final Score score;

    public Cards(List<Card> cards) {
        this.cards = cards;
        this.score = new Score(cards);
    }

    public int calculateMaxScore() {
        return score.calculateMaxScore();
    }

    public int calculateMinScore() {
        return score.calculateMinScore();
    }

    public boolean isBlackjack() {
        return score.isBlackjack();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void take(Card card1, Card card2) {
        this.cards.addAll(Arrays.asList(card1, card2));
    }

    public void additionalTake(Card card) {
        int minScore = score.calculateMaxScore();
        if (minScore >= BUST_THRESHOLD) {
            throw new IllegalArgumentException("카드 합이 21이 넘으므로 더 받을 수 없습니다.");
        }
        this.cards.add(card);
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
        return Objects.equals(getCards(), cards1.getCards()) && Objects.equals(score,
                cards1.score);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getCards());
        result = 31 * result + Objects.hashCode(score);
        return result;
    }
}
