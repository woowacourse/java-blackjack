package blackjack.domain.card;

import static blackjack.domain.game.GameResult.BLACKJACK_VALUE;

import blackjack.domain.game.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_ADDITIONAL_VALUE = 10;

    private final List<Card> value;

    public Cards() {
        this.value = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public void add(Card card) {
        this.value.add(card);
    }

    public int getCount() {
        return value.size();
    }

    public int sum() {
        int sum = value.stream()
                .mapToInt(Card::toInt)
                .sum();

        if (canAddAdditionalValue(sum)) {
            sum += ACE_ADDITIONAL_VALUE;
        }

        return sum;
    }

    public Status getStatus() {
        return Status.findStatus(getCount(), sum());
    }

    private boolean canAddAdditionalValue(int sum) {
        return hasAce() && !exceedBust(sum);
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    private boolean exceedBust(int sum) {
        return sum + ACE_ADDITIONAL_VALUE > BLACKJACK_VALUE;
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(value);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "value=" + value +
                '}';
    }
}
