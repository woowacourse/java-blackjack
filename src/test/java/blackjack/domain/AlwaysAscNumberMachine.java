package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlwaysAscNumberMachine implements CardPickMachine {

    private List<Integer> cardIndex;
    public AlwaysAscNumberMachine() {
        this.cardIndex = IntStream.range(0,52)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public int assignIndex() {
        return cardIndex.remove(0);
    }
}
