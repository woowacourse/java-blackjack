package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final List<Card> hand = new ArrayList<>();

    public Card reveal() {
        return hand.getFirst();
    }

    public final
}
