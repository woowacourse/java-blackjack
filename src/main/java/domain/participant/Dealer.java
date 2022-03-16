package domain.participant;

import java.util.List;

import domain.card.Hand;

public class Dealer extends Participant {

	private static final String DEALER_NAME = "딜러";
	private static final int STANDARD_OF_ENOUGH_CARD = 16;

	public Dealer(Hand hand) {
		super(new Name(DEALER_NAME), hand);
	}

	public boolean isEnoughCard() {
		return hand.getBestScore() > STANDARD_OF_ENOUGH_CARD;
	}

	public ParticipantDTO getOneHandInfo() {
		return new ParticipantDTO(
			name.getInfo(),
			List.of(hand.getOneHand().getInfo())
		);
	}
}
