package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayingPlayingCardShuffleMachine implements PlayingCardPickMachine {

    private static final List<Integer> cardIndex;
    private static final int CARD_NUMBER = Suit.values().length * Denomination.values().length;
    private static final int POP = 0;

    static {
         cardIndex = IntStream.range(0, CARD_NUMBER)
                 .boxed()
                 .collect(Collectors.toList());
    }

    @Override
    public int assignIndex() {
        Collections.shuffle(cardIndex);
        return cardIndex.remove(POP);
    }
}
