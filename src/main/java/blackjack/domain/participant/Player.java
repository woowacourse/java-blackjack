package blackjack.domain.participant;

public class Player extends Gamer {
	private Profit profit;

	public Player(String name) {
		super(name);
	}

	public void makeProfit(double askMoney) {
		this.profit = new Profit(askMoney);
	}

	public void calculateProfit(Dealer dealer) {
		profit = Profit.of(playerState.makeProfit(dealer, profit));
	}

	@Override
	public boolean canReceiveCard(boolean drawFlag) {
		playerState = playerState.keepContinue(drawFlag);
		return !playerState.isFinished();
	}

	public int getMoney() {
		return (int)profit.getMoney();
	}
}
