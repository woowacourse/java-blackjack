package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.vo.Name;
import java.util.List;

public class Player extends User {

    private static final int INIT_COUNT = 2;

    private final BettingMoney money;

    private Player(Name name, BettingMoney money) {
        super(name);
        this.money = money;
    }

    public static Player from(Name name, BettingMoney money) {
        return new Player(name, money);
    }

    public int getRevenue(double earningRate) {
        return money.calculateRevenue(earningRate);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public List<Card> showInitCards() {
        return cards.getLimitedCard(INIT_COUNT);
    }

    @Override
    public boolean isDrawable() {
        return cards.isLessThanBlackJackScore();
    }
}
