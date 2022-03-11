package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardShuffleMachine implements CardPickMachine{

    private static final List<Integer> CARD_INDEX;
    public static final int CARD_MIN_INDEX = 0;
    public static final int CARD_MAX_INDEX = 52;
    public static final int FIRST_INDEX = 0;

    static {
         CARD_INDEX = IntStream.range(CARD_MIN_INDEX, CARD_MAX_INDEX).boxed().collect(Collectors.toList());
    }

    @Override
    public int assignIndex() {
        Collections.shuffle(CARD_INDEX);
        return CARD_INDEX.remove(FIRST_INDEX);
    }
}
