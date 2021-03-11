package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Profit;

public class BlackJack extends Finished {
	public static final double BLACKJACK_PROFIT_RATE = 1.5;

	public BlackJack(Cards cards) {
		super(cards);
	}

	@Override
	public boolean isBust() {
		return false;
	}

	@Override
	public boolean isBlackJack() {
		return true;
	}

	@Override
	public double makeProfit(Dealer dealer, Profit profit) {
		if (dealer.getPlayerState().isBlackJack()) {
			return profit.calculateMoneyWithProfit(WIN_PROFIT_RATE);
		}
		return profit.calculateMoneyWithProfit(BLACKJACK_PROFIT_RATE);
	}
}
