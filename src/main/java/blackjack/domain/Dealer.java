package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    public static final Score NEED_CARD_CRITERION = new Score(17);

    protected Dealer() {
        super(new Cards(new ArrayList<>()));
    }

    public boolean needMoreCard() {
        return NEED_CARD_CRITERION.isBiggerThan(calculate());
    }

    public void addCard(final List<Card> cards) {
        cards.forEach(this::addCard);
    }
}
