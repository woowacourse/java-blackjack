package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {
    private static final int STANDARD = 16;
    private static final int FIRST_CARD = 0;

    public Dealer() {
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotalValue() <= STANDARD;
    }

    @Override
    public List<Card> show() {
        return Collections.singletonList(cards.cards().get(FIRST_CARD));
    }
}
