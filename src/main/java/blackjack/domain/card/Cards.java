package blackjack.domain.card;

import java.util.LinkedHashSet;
import java.util.Set;

public class Cards {
    private final Set<Card> value;

    public Cards(Set<Card> value) {
        this.value = new LinkedHashSet<>(value);
    }

    public void add(Card card) {
        value.add(card);
    }

    public int getSize() {
        return value.size();
    }

    public Status getStatus() {
        if (sum() > 21) {
            return Status.BUST;
        }

        return Status.NOT_BUST;
    }

    public int sum() {
        int sum = value.stream()
                .mapToInt(Card::getNumberValue)
                .sum();

        if (sum <= 11 && hasAce()) {
            sum += 10;
        }
        return sum;
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    public Card findFirst() {
        return value.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("카드가 한 장도 없습니다."));
    }

    @Override
    public String toString() {
        return "Cards{" +
                "value=" + value +
                '}';
    }
}
