package blackjack.domain.card;

import java.util.LinkedHashSet;
import java.util.Set;

public class Cards {

    public static final int MAX_SCORE = 21;
    private static final int UPGRADE_AMOUNT = 10;

    private final Set<Card> value;

    public Cards() {
        this.value = new LinkedHashSet<>();
    }

    Cards(Set<Card> value) {
        this.value = new LinkedHashSet<>(value);
    }

    public void add(Card card) {
        value.add(card);
    }

    public boolean isBust() {
        return sumValue() > MAX_SCORE;
    }

    public int sumValue() {
        int totalValue = value.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        if (canUpgradeAce(totalValue)) {
            totalValue += UPGRADE_AMOUNT;
        }
        return totalValue;
    }

    private boolean canUpgradeAce(final int totalValue) {
        final boolean hasAce = value.stream()
                .anyMatch(Card::isAce);

        return totalValue + UPGRADE_AMOUNT <= MAX_SCORE && hasAce;
    }

    public Card findFirst() {
        return value.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("카드가 한 장도 없습니다."));
    }

    public Set<Card> getValue() {
        return new LinkedHashSet<>(value);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "value=" + value +
                '}';
    }
}
