package blackjack;

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
        throw new RuntimeException();
    }

    @Override
    public void takeCard(Card newCard) {
        throw new RuntimeException();
    }

    @Override
    public List<Integer> getPossibleSums() {
        return integers;
    }

    @Override
    public int getOptimisticValue() {
        return integers.stream().filter(sum -> sum <= 21)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    @Override
    public Card getCard(int position) {
        throw new RuntimeException();
    }
}
