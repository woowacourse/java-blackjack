package domain.card;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Card {

    private static final Map<Shape, Map<Denomination, Card>> CARD_PACK;

    static {
        final List<Shape> shapes = Shape.findAllCardPattern();
        final List<Denomination> denominations = Denomination.findTotalCardNumber();

        CARD_PACK = makeCacheCards(shapes, denominations);
    }

    private final Shape pattern;
    private final Denomination number;

    private Card(final Shape pattern, final Denomination number) {
        this.pattern = pattern;
        this.number = number;
    }

    private static Map<Shape, Map<Denomination, Card>> makeCacheCards(final List<Shape> shapes,
            final List<Denomination> denominations) {
        return shapes.stream()
                .collect(Collectors.toMap(Function.identity(),
                        pattern -> makeCacheCardNumbers(pattern, denominations)));
    }

    private static Map<Denomination, Card> makeCacheCardNumbers(final Shape shape,
            final List<Denomination> denominations) {
        return denominations.stream()
                .collect(Collectors.toMap(Function.identity(),
                        number -> new Card(shape, number)));
    }

    public static Card of(final Shape shape, final Denomination denomination) {
        try {
            final Map<Denomination, Card> cardNumbers = CARD_PACK.get(shape);

            return cardNumbers.get(denomination);
        } catch (NullPointerException e) {
            throw new IllegalStateException("존재하지 않는 카드입니다", e);
        }
    }

    public int findCardNumber() {
        return number.score();
    }

    public boolean checkAce() {
        return number.checkAce();
    }

    public Shape getCardPattern() {
        return pattern;
    }

    public Denomination getCardNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Card)) {
            return false;
        }
        Card targetCard = (Card) target;
        return pattern == targetCard.pattern && number == targetCard.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }
}
