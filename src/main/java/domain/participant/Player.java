package domain.participant;

public class Player extends Participant {
	private Money bettingMoney;

	public Player(Name name, Money bettingMoney) {
		super(name);
		this.bettingMoney = bettingMoney;
	}

	public boolean isWinByBlackJack(Dealer dealer) {
		return this.isBlackJack() && !dealer.isBlackJack();
	}

	public boolean isWin(Dealer dealer) {
		return ((dealer.isBust() && !this.isBust()) || isHigherThanDealerScore(dealer)) && !this.isWinByBlackJack(
			dealer);
	}

	public boolean isLose(Dealer dealer) {
		return this.isBust() || isLowerThanDealerScore(dealer);
	}

	public boolean isDraw(Dealer dealer) {
		return (this.calculateScore() == dealer.calculateScore()) && !this.isBust();
	}

	private boolean isLowerThanDealerScore(Dealer dealer) {
		return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() < dealer.calculateScore());
	}

	private boolean isHigherThanDealerScore(Dealer dealer) {
		return ((!dealer.isBust() && !this.isBust()) && this.calculateScore() > dealer.calculateScore());
	}

	public double getBettingMoney() {
		return bettingMoney.getAmount();
	}
}
