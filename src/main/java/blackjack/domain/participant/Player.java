package blackjack.domain.participant;

import blackjack.domain.state.PlayerState;

public class Player extends Gamer {
	private Money money;

	public Player(String name, double money) {
		super(name);
		this.money = Money.of(money);
	}

	public void makeMoney(double askMoney) {
		money = Money.of(askMoney);
	}

	public void calculateProfit(PlayerState dealerState) {
		double profit = playerState.makeProfit(dealerState, money);
		money = Money.of(profit);
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
