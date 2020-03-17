package domain.gamer;

public class Player extends Gamer {
	private static final int PLAYER_HIT_CEILING = 21;
	private static final int PLAYER_FIRST_OPENED_COUNT = 2;

	private final Money money;

	public Player(Name name, Money money) {
		super(name);
		this.money = money;
	}

	@Override
	protected int getHitPoint() {
		return PLAYER_HIT_CEILING;
	}

	@Override
	protected int firstOpenedCardsCount() {
		return PLAYER_FIRST_OPENED_COUNT;
	}

	public Money getMoney() {
		return money;
	}
}
