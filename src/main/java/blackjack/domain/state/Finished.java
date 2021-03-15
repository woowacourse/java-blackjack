package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;

import java.util.stream.Stream;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public abstract class Finished implements PlayerState {
	protected static final int WIN_PROFIT_RATE = 1;
	protected static final int DRAW_PROFIT_RATE = 0;
	protected static final int LOSE_PROFIT_RATE = -1;

	protected ParticipantCards participantCards;

	public Finished(ParticipantCards participantCards) {
		this.participantCards = participantCards;
	}

	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public PlayerState keepContinue(boolean input) {
		return this;
	}

	@Override
	public PlayerState drawNewCard(Card card) {
		throw new IllegalArgumentException(ERROR_MESSAGE_CALL);
	}

	@Override
	public int calculatePoint() {
		return participantCards.calculateIncludeAce();
	}

	@Override
	public Stream<Card> getCardStream() {
		return participantCards.getCardStream();
	}
}
