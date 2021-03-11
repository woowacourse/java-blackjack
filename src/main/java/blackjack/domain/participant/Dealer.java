package blackjack.domain.participant;

import static blackjack.controller.BlackJackController.*;

import blackjack.domain.card.Deck;

public class Dealer extends Gamer {
	public static final String NAME_OF_DEALER = "딜러";
	public static final int MAX_OF_RECEIVE_MORE_CARD = 16;

	public Dealer() {
		super(NAME_OF_DEALER);
	}

	@Override
	public boolean canReceiveCard() {
		if (this.playerState.calculatePoint() <= MAX_OF_RECEIVE_MORE_CARD) {
			playerState = playerState.keepContinue(true);
			return true;
		}
		if (this.playerState.calculatePoint() > MAX_OF_RECEIVE_MORE_CARD) {
			playerState = playerState.keepContinue(false);
			return false;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_CALL);
	}

	@Override
	public boolean continueDraw(String draw, Deck deck) {
		return true;
	}
}
