package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;
import blackjack.domain.state.Hit;

import java.util.List;

public class Player extends Gamer {

    private final Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    public double getProfit() {
        if (state instanceof Hit) {
            state = state.stay();
        }
        return state.profit(money.getMoney());
    }

    @Override
    public List<Card> showOpenHands() {
        return state.cards().toList();
    }
}
