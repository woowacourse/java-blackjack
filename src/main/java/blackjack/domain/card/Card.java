package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Card {

    private final Denomination denomination;
    private final Shape shape;

    private Card(Denomination denomination, Shape shape) {
        this.denomination = denomination;
        this.shape = shape;
    }

    public static Card of(Denomination denomination, Shape shape) {
        return CardCache.cache
            .stream()
            .filter(card -> card.denomination == denomination && card.shape == shape)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카드입니다."));
    }

    private static class CardCache {
        static final List<Card> cache;

        static {
            cache = new ArrayList<>();

            for (Denomination denomination : Denomination.values()) {
                for (Shape shape : Shape.values()) {
                    cache.add(new Card(denomination, shape));
                }
            }
        }
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
        return denomination == card.denomination && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, shape);
    }
}
