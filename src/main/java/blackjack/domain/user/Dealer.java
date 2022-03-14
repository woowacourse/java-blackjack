package blackjack.domain.user;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends User {

    private static final int INIT_COUNT = 1;
    private static final int DRAW_THRESHOLD = 17;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public List<Card> showInitCards() {
        return cards.getLimitedCard(INIT_COUNT);
    }

    @Override
    public boolean isDrawable() {
        int sumPoint = cards.getSumPoint();

        return sumPoint < DRAW_THRESHOLD;
    }
}
