package blackjack.domain.participant;

public class Player extends Participant {

	private final Money betAmount;

	public Player(String name, int money) {
		this(new Name(name), new Money(money));
	}

	public Player(String name, Money money) {
		this(new Name(name), money);
	}

	public Player(Name name, Money money) {
		super(name);
		this.betAmount = money;
	}

	@Override
	public int getScore() {
		return super.getCards().sum();
	}

	public int getBetAmount() {
		return betAmount.getAmount();
	}
}
