package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardShuffleMachine implements CardPickMachine{

    private static final List<Integer> cardIndex;

    static {
         cardIndex = IntStream.range(0, 52).boxed().collect(Collectors.toList());
    }

    @Override
    public int assignIndex() {
        Collections.shuffle(cardIndex);
        return cardIndex.remove(0);
    }
}
