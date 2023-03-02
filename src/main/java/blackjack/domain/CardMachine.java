package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class CardMachine {

    // TODO: 변수명 생각
    private final List<Integer> keys;

    public CardMachine() {
        this.keys = shuffleDeck();
    }

    private List<Integer> shuffleDeck() {
        List<Integer> keys = Deck.getKeys();
        Collections.shuffle(keys);
        return keys;
    }

    public Integer draw() {
        return keys.remove(0);
    }
}
