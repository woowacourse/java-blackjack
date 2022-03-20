package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public abstract class Finished extends Started {
	private static final String FINISHED_DRAW_EXCEPTION = "끝난 상태에서는 패를 받을 수 없습니다.";
	private static final String FINISHED_STAY_EXCEPTION = "끝난 상태에서는 stay를 할 수 없습니다.";

	protected Finished(Cards cards) {
		super(cards);
	}

	@Override
	public State draw(Card card) {
		throw new IllegalStateException(FINISHED_DRAW_EXCEPTION);
	}

	@Override
	public State stay() {
		throw new IllegalStateException(FINISHED_STAY_EXCEPTION);
	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public Money calculateProfit(Money money, Dealer dealer) {
		return money.multiply(profitRate(dealer));
	}

	protected abstract double profitRate(Dealer dealer);
}
