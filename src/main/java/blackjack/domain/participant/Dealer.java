package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Dealer extends Gamer {
	public static final String NAME_OF_DEALER = "딜러";
	public static final int MAX_OF_RECEIVE_MORE_CARD = 16;

	public Dealer() {
		super(NAME_OF_DEALER);
	}

	@Override
	public boolean canReceiveCard() {
		return this.makeJudgingPoint() <= MAX_OF_RECEIVE_MORE_CARD;
	}

	@Override
	public boolean continueDraw(String draw, Deck deck) {
		this.receiveCard(deck.dealCard());
		return true;
	}

	public boolean isSmallerThan(int playerMaximum) {
		return this.makeFinalPoint() < playerMaximum;
	}

	public boolean isBiggerThan(int playerMaximum) {
		return this.makeFinalPoint() > playerMaximum;
	}

	public boolean isSameThan(int playerMaximum) {
		return this.makeFinalPoint() == playerMaximum;
	}
}
