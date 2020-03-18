package domain.user;

import java.util.Arrays;
import java.util.Objects;

import domain.card.Card;
import domain.card.Cards;

public class Player extends User {
	private final Money money;

	private static final int FIRST_SHOW_SIZE = 2;

	private Player(Name name, Money money) {
		super(name);
		this.money = money;
	}

	private Player(Name name, Money money, Cards cards) {
		super(name, cards);
		this.money = money;
	}

	public static Player fromNameAndMoney(String name, int money) {
		return new Player(new Name(name), Money.of(money));
	}

	public static Player fromNameAndMoneyAndCards(String name, int bettingMoney, Card... cards) {
		return new Player(new Name(name), Money.of(bettingMoney),
			new Cards(Arrays.asList(Objects.requireNonNull(cards))));
	}

	@Override
	public boolean isDrawable() {
		return !isBlackjack() && !isBust();
	}

	public Money getMoney() {
		return money;
	}

	@Override
	protected int getFirstShowCardSize() {
		return FIRST_SHOW_SIZE;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Player that = (Player)object;
		return Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
