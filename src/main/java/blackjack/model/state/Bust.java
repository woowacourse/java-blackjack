package blackjack.model.state;

import blackjack.model.cards.Cards;
import blackjack.vo.Money;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public Money calculateProfit(Money betMoney) {
        return new Money(-betMoney.value());
    }
}
