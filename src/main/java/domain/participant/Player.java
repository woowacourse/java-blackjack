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
		if (isPlayerWinByBlackJack(dealer)) {
			return bettingMoney.getAmount() * BLACKJACK_PROFIT_RATE;
		}
		if (isPlayerLose(dealer)) {
			return bettingMoney.getAmount() * MINUS_PROFIT;
		}
		if (isPlayerWin(dealer)) {
			return bettingMoney.getAmount();
		}
		return 0;
	}

	private boolean isPlayerWinByBlackJack(Dealer dealer) {
		return this.isBlackJack() && !dealer.isBlackJack();
	}

	private boolean isPlayerWin(Dealer dealer) {
		return dealer.isBust() || isHigherThanDealerScore(dealer);
	}

	private boolean isLowerThanDealerScore(Dealer dealer) {
		return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() < dealer.calculateScore());
	}

	private boolean isPlayerLose(Dealer dealer) {
		return this.isBust() || isLowerThanDealerScore(dealer);
	}

	private boolean isHigherThanDealerScore(Dealer dealer) {
		return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() > dealer.calculateScore());
	}

}
