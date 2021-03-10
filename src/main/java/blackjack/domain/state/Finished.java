package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Finished implements PlayerState {
	@Override
	public boolean isFinished() {
		return true;
	}

	@Override
	public PlayerState keepContinue(boolean input) {
		throw new IllegalArgumentException("옳지 않은 곳에서 호출");
	}

	@Override
	public PlayerState checkStateWithNewCard(Cards cards) {
		throw new IllegalArgumentException("옳지 않은 곳에서 호출");
	}
}
