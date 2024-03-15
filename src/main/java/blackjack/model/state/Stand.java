package blackjack.model.state;

import blackjack.model.cards.Cards;
import blackjack.vo.Money;

public class Stand extends Finished {
    public Stand(Cards cards) {
        super(cards);
    }

    @Override
    public Money calculateProfit(Money betMoney) {
        return new Money(betMoney.value());
    }
}
