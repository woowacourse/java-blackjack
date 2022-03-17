package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;
import blackjack.utils.IntegerUtils;

public class Player extends User {

    private static final int INIT_COUNT = 2;

    private final Money money;

    public Player(String name, int money) {
        super(name);
        this.money = new Money(money);
    }

    public static Player fromNameAndMoney(String inputName, String inputMoney) {
        return new Player(inputName, IntegerUtils.parseInt(inputMoney));
    }

    @Override
    public List<Card> showInitCards() {
        return this.state.getCards(INIT_COUNT);
    }
}
