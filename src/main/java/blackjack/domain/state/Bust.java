package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;

public class Bust extends Finished {
	public Bust(Cards cards) {
		super(cards);
	}

	@Override
	public boolean isBust() {
		return true;
	}

	@Override
	public boolean isBlackJack() {
		return false;
	}

	@Override
	public double makeProfit(Dealer dealer, Money money) {
		return money.calculateMoneyWithProfit(LOSE_PROFIT_RATE);
	}
}
