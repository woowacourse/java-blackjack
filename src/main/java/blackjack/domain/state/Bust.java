package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Profit;

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
	public double makeProfit(Dealer dealer, Profit profit) {
		return profit.calculateMoneyWithProfit(LOSE_PROFIT_RATE);
	}
}
