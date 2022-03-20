package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished extends Started {

	private static final String FINISHED_STATE_ERROR = "턴이 종료된 상태입니다.";

	Finished(Cards cards) {
		super(cards);
	}

	@Override
	public State draw(Card card) {
		throw new IllegalStateException(FINISHED_STATE_ERROR);
	}

	@Override
	public State stay() {
		throw new IllegalStateException(FINISHED_STATE_ERROR);
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
