package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Cards {

    private final List<Card> values;

    public Cards(final List<Card> cards) {
        validateDuplicate(cards);
        this.values = new ArrayList<>(cards);
    }

    public Cards(final Card... cards) {
        this(List.of(cards));
    }

    private void validateDuplicate(final List<Card> cards) {
        if (Set.copyOf(cards).size() != cards.size()) {
            throw new IllegalStateException("중복된 카드는 존재할 수 없습니다.");
        }
    }

    public int countAce() {
        return (int) values.stream()
                .filter(Card::isAce)
                .count();
    }

    public int sum() {
        return values.stream()
                .mapToInt(Card::getRealNumber)
                .sum();
    }

    public void add(final Card card) {
        values.add(card);
    }
}