package blackjack.model.state;

import blackjack.model.cards.Cards;
import blackjack.vo.Money;

public class BlackJack extends Finished {
    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public Money calculateProfit(Money betMoney) {
        return new Money((int) ((betMoney.value()) * 1.5));
    }
}
