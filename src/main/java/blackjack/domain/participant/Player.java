package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Player extends Gamer {
	private static final String ERROR_MESSAGE_OF_Y_OR_N = "y 혹은 n 만 입력하여 주십시오.";
	private static final String AGREE = "y";
	private static final String DISAGREE = "n";

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

	public int getMoney() {
		return (int)profit.getMoney();
	}

	@Override
	public boolean canReceiveCard() {
		return !playerState.isFinished();
	}

	@Override
	public boolean continueDraw(String draw, Deck deck) {
		if (!AGREE.equals(draw) && !DISAGREE.equals(draw)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_OF_Y_OR_N);
		}
		if (AGREE.equals(draw)) {
			playerState = playerState.keepContinue(true);
		}
		if (DISAGREE.equals(draw)) {
			playerState = playerState.keepContinue(false);
		}
		return canReceiveCard();
	}
}
