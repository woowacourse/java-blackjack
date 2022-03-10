package domain.participant;

import java.util.List;

import domain.card.Card;

public class Dealer extends Participant {

	public static final String DEALER_NAME = "딜러";
	public static final int STANDARD_OF_ENOUGH_CARD = 16;
	public static final int FIRST_CARD_INDEX = 0;
	public static final String SHOW_ONE_HAND_FORMAT = "%s: %s";

	public Dealer(List<Card> hand) {
		super(new Name(DEALER_NAME), hand);
	}

	public boolean isEnoughCard() {
		return getBestScore() > STANDARD_OF_ENOUGH_CARD;
	}

	public String showOneHand() {
		return String.format(SHOW_ONE_HAND_FORMAT, name.getName(), hand.get(FIRST_CARD_INDEX));
	}
}
