package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class ShufflingMachine {

    private final List<Integer> keys;

    public ShufflingMachine() {
        this.keys = shuffleDeck();
    }

    private List<Integer> shuffleDeck() {
        final List<Integer> keys = Deck.getKeys();
        Collections.shuffle(keys);
        return keys;
    }

    public Integer draw() {
        return keys.remove(0);
    }
}
