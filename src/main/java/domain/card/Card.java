package domain.card;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Card {

    private static final Map<CardPattern, Map<CardNumber, Card>> cache;

    static {
        final List<CardPattern> cardPatterns = CardPattern.findAllCardPattern();
        final List<CardNumber> cardNumbers = CardNumber.findTotalCardNumber();

        cache = makeCacheCards(cardPatterns, cardNumbers);
    }

    private final CardPattern pattern;
    private final CardNumber number;

    private Card(final CardPattern pattern, final CardNumber number) {
        this.pattern = pattern;
        this.number = number;
    }

    private static Map<CardPattern, Map<CardNumber, Card>> makeCacheCards(final List<CardPattern> cardPatterns,
            final List<CardNumber> cardNumbers) {
        return cardPatterns.stream()
                .collect(Collectors.toMap(Function.identity(),
                        pattern -> makeCacheCardNumbers(pattern, cardNumbers)));
    }

    private static Map<CardNumber, Card> makeCacheCardNumbers(final CardPattern cardPattern,
            final List<CardNumber> cardNumbers) {
        return cardNumbers.stream()
                .collect(Collectors.toMap(Function.identity(),
                        number -> new Card(cardPattern, number)));
    }

    public static Card of(final CardPattern cardPattern, final CardNumber cardNumber) {
        try {
            final Map<CardNumber, Card> cardNumbers = cache.get(cardPattern);

            return cardNumbers.get(cardNumber);
        } catch (NullPointerException e) {
            throw new IllegalStateException("존재하지 않는 카드입니다", e);
        }
    }

    public int findCardNumber() {
        return number.findNumber();
    }

    public boolean checkAce() {
        return number.checkAce();
    }

    public CardPattern getCardPattern() {
        return pattern;
    }

    public CardNumber getCardNumber() {
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
