package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.vo.Name;
import java.util.List;

public class Dealer extends User {

    private static final int INIT_COUNT = 1;
    private static final int DRAW_THRESHOLD = 17;

    private Dealer(Name name) {
        super(name);
    }

    public static Dealer create() {
        Name name = Name.of("딜러");

        return new Dealer(name);
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
