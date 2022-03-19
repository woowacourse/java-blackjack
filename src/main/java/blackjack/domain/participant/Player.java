package blackjack.domain.participant;

import blackjack.domain.Money;

public class Player extends Participant {

	private final Money betAmount;

	public Player(Name name, Money money) {
		super(name);
		this.betAmount = money;
	}

	public Player(String name, int money) {
		super(new Name(name));
		this.betAmount = new Money(money);
	}

	public boolean shouldHit(String input) {
		return input.equals("y");
	}

	@Override
	public int getScore() {
		return super.getCards().sum();
	}

	public int getBetAmount() {
		return betAmount.getAmount();
	}
}
