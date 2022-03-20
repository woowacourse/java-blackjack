package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Ready extends Running {

	private static final String CAN_NOT_STAY_ERROR = "현재 상태에서는 스테이할 수 없습니다.";

	public Ready() {
		super(new Cards());
	}

	private Ready(Cards cards) {
		super(cards);
	}

	@Override
	public final State draw(final Card card) {
		final Cards cards = this.cards.add(card);
		if (cards.getSize() < 2) {
			return new Ready(cards);
		}
		if (cards.isBlackjack()) {
			return new Blackjack(cards);
		}
		return new Hit(cards);
	}

	@Override
	public final Stay stay() {
		throw new IllegalStateException(CAN_NOT_STAY_ERROR);
	}
}
