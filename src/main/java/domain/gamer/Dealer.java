package domain.gamer;

public class Dealer extends Gamer {
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_HIT_CEILING = 17;

	public Dealer() {
		super(new Name(DEALER_NAME));
	}

	@Override
	public int getHitPoint() {
		return DEALER_HIT_CEILING;
	}
}