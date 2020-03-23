package domain.gamer;

import domain.result.Profit;

public class Player extends Gamer {
	private static final int PLAYER_HIT_POINT = 21;

	private final Money money;

	public Player(Name name, Money money) {
		super(name);
		this.money = money;
	}

	public Profit createProfit(double times) {
		return new Profit(money.multiply(times));
	}

	@Override
	public int getHitPoint() {
		return PLAYER_HIT_POINT;
	}

	public Money getMoney() {
		return money;
	}
}
