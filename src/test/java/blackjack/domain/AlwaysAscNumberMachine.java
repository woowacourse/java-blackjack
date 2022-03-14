package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class AlwaysAscNumberMachine implements CardPickMachine {

    private final List<Integer> cardIndex;

    public AlwaysAscNumberMachine() {
        this.cardIndex = IntStream.range(0, 52)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public int assignIndex() {
        return cardIndex.remove(0);
    }
}
