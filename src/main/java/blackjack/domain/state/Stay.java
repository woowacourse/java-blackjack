package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.game.MatchResult;

public final class Stay extends Finished {

	Stay(Cards cards) {
		super(cards);
	}

	@Override
	public MatchResult match(State state) {
		Cards cards = state.getCards();
		if (cards.isBlackJack()) {
			return MatchResult.LOSE;
		}
		if (cards.isBust()) {
			return MatchResult.WIN;
		}
		return MatchResult.compare(this.cards.sum(), cards.sum());
	}
}
