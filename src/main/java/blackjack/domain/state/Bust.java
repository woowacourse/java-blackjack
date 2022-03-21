package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.game.MatchResult;

public final class Bust extends Finished {

	Bust(Cards cards) {
		super(cards);
	}

	@Override
	public MatchResult match(State state) {
		Cards cards = state.getCards();
		if (cards.isBust()) {
			return MatchResult.DRAW;
		}
		return MatchResult.LOSE;
	}
}
