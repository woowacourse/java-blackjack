package blackjack;

import blackjack.domain.Card;
import blackjack.domain.CardHolder;
import java.util.List;

public class StubPossibleSumCardHolder implements CardHolder {

    private final List<Integer> integers;

    public StubPossibleSumCardHolder(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public List<Card> getAllCards() {
        return List.of();
    }

    @Override
    public void takeCard(Card newCard) {

    }

    @Override
    public List<Integer> getPossibleSums() {
        return integers;
    }
}
