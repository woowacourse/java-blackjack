package domain.user;

import domain.user.strategy.draw.PlayerDrawStrategy;

import java.util.Objects;

public class Player extends User {
	private final Name name;
	private final BettingMoney bettingMoney;

	public Player(Name name, BettingMoney bettingMoney) {
		this.name = name;
		this.bettingMoney = bettingMoney;
		super.drawStrategy = new PlayerDrawStrategy();
	}

	public Player(String name) {
		this(new Name(name), new BettingMoney(1));
	}

	@Override
	public String toString() {
		return name.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Player player = (Player) o;
		return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
