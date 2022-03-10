package blackjack.domain.user;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class Dealer extends User {

    private static final int INIT_COUNT = 1;

    public Dealer() {
        super("딜러");
    }

    @Override
    public List<Card> showInitCards() {
        return Collections.unmodifiableList(cards.subList(0, INIT_COUNT));
    }
}
