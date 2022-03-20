package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player extends Participant {

    private static final int FIRST_OPEN_COUNT = 2;

    private final BettingMoney bettingMoney;

    public Player(String name, Cards cards) {
        super(name, cards);
        this.bettingMoney = BettingMoney.emptyMoney();
    }

    public Player(String name, int money, Cards cards) {
        super(name, cards);
        this.bettingMoney = new BettingMoney(money);
    }

    public double findProfit(Dealer dealer) {
        return state.profit(dealer, bettingMoney.getValue());
    }

    public boolean isHit(DrawStatus drawStatus) {
        return drawStatus == DrawStatus.YES;
    }

    @Override
    public List<Card> openFirstCards() {
        return getCards().subList(0, FIRST_OPEN_COUNT);
    }
}
