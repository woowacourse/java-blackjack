package domain.participant;

public class Player extends Participant {
	public static final double BLACKJACK_PROFIT_RATE = 1.5;
	public static final int MINUS_PROFIT = -1;
	private Money bettingMoney;

	public Player(Name name, Money bettingMoney) {
		super(name);
		this.bettingMoney = bettingMoney;
	}

	public void setBettingMoney(Money bettingMoney) {
		this.bettingMoney = bettingMoney;
	}

	public double computeProfit(Dealer dealer) {
		if (this.isBlackJack() && !dealer.isBlackJack()) {
			return bettingMoney.getAmount() * BLACKJACK_PROFIT_RATE;
		}
		if (this.isBust() || isLowerThanDealerScore(dealer)) {
			return bettingMoney.getAmount() * MINUS_PROFIT;
		}
		if (dealer.isBust() || isHigherThanDealerScore(dealer)) {
			return bettingMoney.getAmount();
		}
		return 0;
	}

	private boolean isLowerThanDealerScore(Dealer dealer) {
		return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() < dealer.calculateScore());
	}

	private boolean isHigherThanDealerScore(Dealer dealer) {
		return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() > dealer.calculateScore());
	}

}
