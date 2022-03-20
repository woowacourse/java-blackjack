package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public abstract class Running extends Started{
	private static final String RUNNING_PROFIT_EXCEPTION = "게임 진행중에는 profit 계산을 할 수 없습니다";

	protected Running(Cards cards) {
		super(cards);
	}

	@Override
	public Money calculateProfit(Money money, Dealer dealer) {
		throw new IllegalStateException(RUNNING_PROFIT_EXCEPTION);
	}

	@Override
	public boolean isRunning() {
		return true;
	}
}
