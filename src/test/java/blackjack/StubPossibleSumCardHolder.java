package blackjack;

import blackjack.common.Constants;
import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import java.util.Comparator;
import java.util.List;

public class StubPossibleSumCardHolder implements CardHolder {

    private final List<Integer> integers;

    public StubPossibleSumCardHolder(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public List<Card> getAllCards() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void takeCard(Card newCard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getOptimisticValue() {
        return integers.stream().filter(sum -> sum <= 21)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    @Override
    public Card getCard(int position) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBusted() {
        return integers.stream()
                .allMatch(sum -> sum > Constants.BUSTED_STANDARD_VALUE);
    }

    @Override
    public boolean canTakeCardWithin(int takeBoundary) {
        return integers.stream()
                .anyMatch(sum -> sum <= takeBoundary);
    }
}
