package blackjack.domain.participant;

public class Player extends Gamer {
	private Money money;

	public Player(String name) {
		super(name);
	}

	public void makeProfit(double askMoney) {
		this.money = new Money(askMoney);
	}

	public Money calculateProfit(Dealer dealer) {
		money = Money.of(playerState.makeProfit(dealer, money));
		return money;
	}

	@Override
	public boolean canReceiveCard(boolean drawFlag) {
		playerState = playerState.keepContinue(drawFlag);
		return !playerState.isFinished();
	}

	public int getMoney() {
		return money.getMoney();
	}
}
