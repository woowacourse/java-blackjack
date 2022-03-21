package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.game.MatchResult;

public abstract class Running extends Started {

	public static final String RUNNING_STATE_ERROR = "턴이 종료되지 않았습니다.";

	Running(Cards cards) {
		super(cards);
	}

	@Override
	public State stay() {
		return new Stay(cards);
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public MatchResult match(State state) {
		throw new IllegalStateException(RUNNING_STATE_ERROR);
	}
}
