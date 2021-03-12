package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;

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
	public double makeProfit(Dealer dealer, Money money) {
		if (dealer.calculatePoint() == calculatePoint()) {
			return money.calculateMoneyWithProfit(DRAW_PROFIT_RATE);
		}
		if (!dealer.getPlayerState().isBust() && dealer.calculatePoint() > calculatePoint()) {
			return money.calculateMoneyWithProfit(LOSE_PROFIT_RATE);
		}
		return money.calculateMoneyWithProfit(WIN_PROFIT_RATE);
	}
}
