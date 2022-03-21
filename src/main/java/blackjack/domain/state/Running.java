package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public abstract class Running implements State {
	private static final String RUNNING_PROFIT_EXCEPTION = "게임 진행중에는 profit 계산을 할 수 없습니다";

	protected final Cards cards;

	protected Running(Cards cards) {
		this.cards = cards;
	}

	@Override
	public double profitRate(Dealer dealer) {
		throw new IllegalStateException(RUNNING_PROFIT_EXCEPTION);
	}

	@Override
	public boolean isRunning() {
		return true;
	}

	@Override
	public Cards getCards() {
		return this.cards;
	}
}
