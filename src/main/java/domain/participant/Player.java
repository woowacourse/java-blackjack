package domain.participant;

public class Player extends Participant {
	private Money bettingMoney;

	public Player(Name name, Money bettingMoney) {
		super(name);
		this.bettingMoney = bettingMoney;
	}

	public void setBettingMoney(Money bettingMoney) {
		this.bettingMoney = bettingMoney;
	}

	public double beatDealer(Dealer dealer) {
		if (this.isBlackJack() && dealer.isBlackJack()) {
			return 0;
		}
		if (this.isBlackJack()) {
			return bettingMoney.getAmount() * (1.5);
		}
		if (this.isBust() || isLowerThanDealerScore(dealer)) {
			return bettingMoney.getAmount() * (-1);
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
