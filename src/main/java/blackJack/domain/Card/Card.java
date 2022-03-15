package blackJack.domain.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Card {
    private static final int TOTAL_CARDS_SIZE = 52;
    private static final List<Card> CACHE = new ArrayList<>(TOTAL_CARDS_SIZE);

    private final Shape shape;
    private final Number number;

    static {
        Arrays.stream(Shape.values())
                .forEach(shape -> Arrays.stream(Number.values())
                        .map(number -> new Card(shape, number))
                        .forEach(CACHE::add));
    }

    public Card(Shape shape, Number number) {
        this.shape = shape;
        this.number = number;
    }

    public static Card valueOf(Shape shape, Number number) {
        return CACHE.stream().filter(card -> card.isSameShape(shape) && card.isSameNumber(number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 없는 카드 입니다."));
    }

    public Shape getShape() {
        return shape;
    }

    public Number getNumber() {
        return number;
    }

    public boolean isAce() {
        return number.isAce();
    }

    private boolean isSameShape(Shape shape) {
        return this.shape == shape;
    }

    private boolean isSameNumber(Number number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}
