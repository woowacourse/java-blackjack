package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Profit;

public class Stay extends Finished {
	public Stay(Cards cards) {
		super(cards);
	}

	@Override
	public boolean isBust() {
		return false;
	}

	@Override
	public boolean isBlackJack() {
		return false;
	}

	@Override
	public double makeProfit(Dealer dealer, Profit profit) {
		if (dealer.calculatePoint() == calculatePoint()) {
			return profit.calculateMoneyWithProfit(DRAW_PROFIT_RATE);
		}
		if (!dealer.getPlayerState().isBust() && dealer.calculatePoint() > calculatePoint()) {
			return profit.calculateMoneyWithProfit(LOSE_PROFIT_RATE);
		}
		return profit.calculateMoneyWithProfit(WIN_PROFIT_RATE);
	}
}
