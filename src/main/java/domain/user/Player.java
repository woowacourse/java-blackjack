package domain.user;

import java.util.List;
import java.util.Objects;

import domain.betting.Money;
import domain.card.Card;

public class Player extends User {
	private static final int INITIAL_FROM_INDEX = 0;
	private static final int INITIAL_TO_INDEX = 2;
	private static final String WRONG_NAME_MESSAGE = "이름에 빈값이 들어갈 수 없습니다.";
	private static final String DEFAULT_MONEY = "0";

	private final String name;
	private final Money money;

	public Player(String name) {
		this(name, DEFAULT_MONEY);
	}
	public Player(String name, String money) {
		this.money = Money.from(Objects.requireNonNull(money));
		validEmptyAndNull(name);
		this.name = name;
	}

	private void validEmptyAndNull(String name) {
		if (Objects.isNull(name) || name.isEmpty()) {
			throw new IllegalArgumentException(WRONG_NAME_MESSAGE);
		}

	}

	public int calculateProfit(double profitRate) {
		return money.calculateProfit(profitRate);
	}

	public  int calculateDifferent(int money) {
		return this.money.calculateDifferent(money);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player)o;
		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public List<Card> getInitialCard() {
		return cards.getCards()
			.subList(INITIAL_FROM_INDEX, INITIAL_TO_INDEX);
	}

	public Money getMoney() {
		return money;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
