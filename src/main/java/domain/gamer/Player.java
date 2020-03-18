package domain.gamer;

public class Player extends Gamer {
	private static final int PLAYER_HIT_POINT = 21;

	private final Money money;

	public Player(Name name, Money money) {
		super(name);
		this.money = money;
	}

	@Override
	public int getHitPoint() {
		return PLAYER_HIT_POINT;
	}

	public Money getMoney() {
		return money;
	}
}
