package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.game.MatchResult;

public final class BlackJack extends Finished {

	BlackJack(Cards cards) {
		super(cards);
	}

	@Override
	public MatchResult match(State state) {
		Cards cards = state.getCards();
		if (cards.isBlackJack()) {
			return MatchResult.DRAW;
		}
		return MatchResult.BLACKJACK;
	}
}
