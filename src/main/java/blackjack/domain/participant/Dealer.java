package blackjack.domain.participant;

public class Dealer extends Participant {
	private static final String DEALER_NAME = "딜러";
	private static final int DEALER_HIT_CARD_STANDARD = 16;

	public Dealer() {
		super(new Name(DEALER_NAME));
	}

	public String getFirstCardName() {
		return getCards().getFirstCardName();
	}

	public boolean shouldHit() {
		return getScore() > DEALER_HIT_CARD_STANDARD;
	}

	@Override
	public int getScore() {
		return super.getCards().sumWithoutCheckAce();
	}
}
