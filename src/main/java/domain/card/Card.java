package domain.card;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Card {

    private static final Map<CardPattern, Map<Denomination, Card>> cache;

    static {
        final List<CardPattern> cardPatterns = CardPattern.findAllCardPattern();
        final List<Denomination> denominations = Denomination.findTotalCardNumber();

        cache = makeCacheCards(cardPatterns, denominations);
    }

    private final CardPattern pattern;
    private final Denomination number;

    private Card(final CardPattern pattern, final Denomination number) {
        this.pattern = pattern;
        this.number = number;
    }

    private static Map<CardPattern, Map<Denomination, Card>> makeCacheCards(final List<CardPattern> cardPatterns,
            final List<Denomination> denominations) {
        return cardPatterns.stream()
                .collect(Collectors.toMap(Function.identity(),
                        pattern -> makeCacheCardNumbers(pattern, denominations)));
    }

    private static Map<Denomination, Card> makeCacheCardNumbers(final CardPattern cardPattern,
            final List<Denomination> denominations) {
        return denominations.stream()
                .collect(Collectors.toMap(Function.identity(),
                        number -> new Card(cardPattern, number)));
    }

    public static Card of(final CardPattern cardPattern, final Denomination denomination) {
        try {
            final Map<Denomination, Card> cardNumbers = cache.get(cardPattern);

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

    public CardPattern getCardPattern() {
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
