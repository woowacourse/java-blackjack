package blackjack.domain.state;

import static blackjack.controller.BlackJackController.*;

import java.util.stream.Stream;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.participant.Money;

public abstract class Running implements PlayerState {
	protected ParticipantCards participantCards;

	public Running(ParticipantCards participantCards) {
		this.participantCards = participantCards;
	}

	@Override
	public int calculatePoint() {
		return participantCards.calculateJudgingPoint();
	}

	@Override
	public double makeProfit(PlayerState comparingState, Money money) {
		throw new IllegalArgumentException(ERROR_MESSAGE_CALL);
	}

	@Override
	public Stream<Card> getCardStream() {
		return participantCards.getCardStream();
	}
}
