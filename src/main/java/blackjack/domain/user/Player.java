package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Money;
import blackjack.domain.user.state.Ready;
import blackjack.domain.user.state.State;

public final class Player extends User {

    private static final int INIT_SHOW_COUNT = 2;

    private final Money money;

    public Player(String inputName, State state, String inputMoney) {
        super(inputName, state);
        this.money = new BettingMoney(inputMoney);
    }

    public static Player of(String inputName, String inputMoney) {
        return new Player(inputName, new Ready(), inputMoney);
    }

    @Override
    public List<Card> showInitCards() {
        return this.state.getInitHandCards(INIT_SHOW_COUNT);
    }

    public Money calculateProfit(Dealer dealer) {
        return state.calculateProfit(money, dealer.state);
    }
}
