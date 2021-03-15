package blackjack.domain.participant;

public class Player extends Gamer {
	private Money money;

	public Player(String name, int money) {
		super(name);
		this.money = Money.of(money);
	}

	public void makeMoney(double askMoney) {
		this.money = Money.of(askMoney);
	}

	public void calculateProfit(Dealer dealer) {
		money = Money.of(playerState.makeProfit(dealer, money));
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
