package domain.gamer;

import domain.card.Hand;
import domain.rule.Rule;

import java.util.function.Function;

public class Player extends Gamer {
	private static final int PLAYER_FIRST_OPENED_COUNT = 2;

	private final Money money;

	public Player(Name name, Money money) {
		super(name);
		this.money = money;
	}

	@Override
	protected Function<Hand, Boolean> hitStrategy() {
		return Rule::canPlayerHit;
	}

	@Override
	protected int firstOpenedCardsCount() {
		return PLAYER_FIRST_OPENED_COUNT;
	}

	public Money getMoney() {
		return money;
	}
}
