package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.cardholder.CardHolder;
import java.util.Comparator;
import java.util.List;

public class StubPossibleSumCardHolder implements CardHolder {

    private final List<Integer> integers;

    public StubPossibleSumCardHolder(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public List<Card> getAllCards() {
        throw new RuntimeException();
    }

    @Override
    public void takeCard(Card newCard) {
        throw new RuntimeException();

    }

    @Override
    public List<Integer> calculatePossibleSums() {
        return integers;
    }

    @Override
    public int getOptimisticValue() {
        return integers.stream().filter(sum -> sum <= 21)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }
}
