package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.Money;

public abstract class Running extends Started {

	private static final String CAN_NOT_PROFIT_ERROR = "아직 게임이 끝나지 않아서 수익을 계산할 수 없습니다.";

	public Running(Cards cards) {
		super(cards);
	}

	public abstract State draw(final Card card);

	public abstract Stay stay();

	@Override
	public final Money profit(Cards cards, Money money) {
		throw new IllegalStateException(CAN_NOT_PROFIT_ERROR);
	}

	@Override
	public final boolean isFinished() {
		return false;
	}
}
