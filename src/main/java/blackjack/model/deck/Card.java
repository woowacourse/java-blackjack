package blackjack.model.deck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
    private static final List<Card> CACHE;

    private final Shape shape;
    private final Score score;

    static {
        CACHE = Arrays.stream(Shape.values())
                .flatMap(Card::matchScore)
                .collect(Collectors.toList());
    }

    private Card(final Shape shape, final Score score) {
        this.shape = shape;
        this.score = score;
    }

    public static List<Card> getCards() {
        return new ArrayList<>(CACHE);
    }

    private static Stream<Card> matchScore(Shape shape) {
        return Arrays.stream(Score.values())
                .map(score -> new Card(shape, score));
    }

    public boolean isAce() {
        return score.isAce();
    }

    public int getScoreValue() {
        return score.getValue();
    }

    public Score getScore() {
        return score;
    }

    public Shape getShape() {
        return shape;
    }

    public static Card from(final Shape shape, final Score score) {
        return CACHE.stream()
                .filter(card -> card.shape == shape && card.score == score)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 카드가 존재하지 않습니다."));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && score == card.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, score);
    }
}
