package blackjack.domain.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class AlwaysAscNumberMachinePlaying implements PlayingCardPickMachine {

    private final List<Integer> cardIndex;

    public AlwaysAscNumberMachinePlaying() {
        this.cardIndex = IntStream.range(0, 52)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public int assignIndex() {
        return cardIndex.remove(0);
    }
}
