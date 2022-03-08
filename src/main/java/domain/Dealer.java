package domain;

import java.util.List;

public class Dealer extends Participant {

	public static final String DEALER_NAME = "딜러";
	public static final int STANDARD_OF_ENOUGH_CARD = 16;

	public Dealer(List<Card> hand) {
		super(DEALER_NAME, hand);
	}

	public boolean isEnoughCard() {
		return getBestScore() > STANDARD_OF_ENOUGH_CARD;
	}
}
