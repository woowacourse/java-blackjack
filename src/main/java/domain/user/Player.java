package domain.user;

import java.util.Arrays;
import java.util.Objects;

import domain.card.Card;
import domain.card.Cards;

public class Player extends User {
	private final Money money;

	private static final int FIRST_SHOW_SIZE = 2;

	private Player(Name name) {
		super(name);
		this.money = Money.of(1000);
	}

	private Player(Name name, Cards cards) {
		this(name, Money.of(1000), cards);
	}

	private Player(Name name, Money money, Cards cards) {
		super(name, cards);
		this.money = money;
	}

	public static Player valueOf(String name) {
		return new Player(new Name(name));
	}

	public static Player fromNameAndCards(String name, Card... cards) {
		return new Player(new Name(name), new Cards(Arrays.asList(Objects.requireNonNull(cards))));
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
