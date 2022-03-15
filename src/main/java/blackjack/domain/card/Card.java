package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> pool = createCardPool();

    private final Pattern pattern;
    private final Denomination denomination;

    public Card(Pattern pattern, Denomination denomination) {
        this.pattern = pattern;
        this.denomination = denomination;
    }

    public static Card of(Pattern pattern, Denomination denomination) {
        return pool.stream()
                .filter(card -> card.pattern == pattern && card.denomination == denomination)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 올바르지 않은 카드입니다."));
    }

    private static List<Card> createCardPool() {
        return Arrays.stream(Pattern.values())
                .map(Card::createCardsBy)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Card> createCardsBy(Pattern pattern) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(pattern, denomination))
                .collect(Collectors.toList());
    }

    public static List<Card> getPool() {
        return pool;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public int getValue() {
        return denomination.getValue();
    }

    public boolean isSameValueWith(Denomination denomination) {
        return this.denomination == denomination;
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

        if (pattern != card.pattern) {
            return false;
        }
        return denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        int result = pattern != null ? pattern.hashCode() : 0;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        return result;
    }
}
