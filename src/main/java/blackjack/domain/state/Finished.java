package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.Money;

public abstract class Finished extends Started {

	protected static final int VICTORY_RATE = 1;
	protected static final int DEFEAT_RATE = -1;
	protected static final int TIE_RATE = 0;

	private static final String CAN_NOT_DRAW_ERROR = "현재 상태에서는 더이상 드로우 할 수 없습니다.";
	private static final String CAN_NOT_STAY_ERROR = "현재 상태에서는 스테이할 수 없습니다.";

	Finished(final Cards cards) {
		super(cards);
	}

	public abstract double earningRate(Cards cards);

	@Override
	public final State draw(final Card card) {
		throw new IllegalStateException(CAN_NOT_DRAW_ERROR);
	}

	@Override
	public final Stay stay() {
		throw new IllegalStateException(CAN_NOT_STAY_ERROR);
	}

	@Override
	public final boolean isFinished() {
		return true;
	}

	@Override
	public final Money profit(Cards cards, Money money) {
		return money.multiply(earningRate(cards));
	}
}
