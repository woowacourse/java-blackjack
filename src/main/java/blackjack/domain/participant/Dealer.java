package blackjack.domain.participant;

public class Dealer extends Participant {
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_ADDITIONAL_CARD_STANDARD = 16;

	public Dealer() {
		super(Name.from(DEALER_NAME));
	}

	@Override
	public boolean shouldHit() {
		return getScore() > DEALER_ADDITIONAL_CARD_STANDARD;
	}
}
