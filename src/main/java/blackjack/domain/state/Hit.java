package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public class Hit extends Running {
	private static final int THRESHOLD_OF_BUST = 21;

	public Hit(ParticipantCards participantCards) {
		super(participantCards);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public PlayerState keepContinue(boolean input) {
		if (!input) {
			return new Stay(participantCards);
		}
		return this;
	}

	@Override
	public PlayerState drawNewCard(Card card) {
		participantCards.addCard(card);
		if (participantCards.calculateIncludeAce() > THRESHOLD_OF_BUST) {
			return new Bust(participantCards);
		}
		return this;
	}

	@Override
	public boolean isBust() {
		return false;
	}

	@Override
	public boolean isBlackJack() {
		return false;
	}
}
