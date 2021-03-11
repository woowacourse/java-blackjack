package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Player extends Gamer {
	private static final String ERROR_MESSAGE_OF_Y_OR_N = "y 혹은 n 만 입력하여 주십시오.";
	private static final String AGREE = "y";
	private static final String DISAGREE = "n";

	private Money money;

	public Player(String name) {
		super(name, 0);
	}

	public void makeBetting(double askMoney) {
		this.money = new Money(askMoney);
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

	public void calculateProfit(Dealer dealer) {
		money = Money.of(playerState.makeProfit(dealer, money));
	}

	public int getMoney() {
		return (int)money.getMoney();
	}
}
