package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class BlackJack extends Finished {

	BlackJack(Cards cards) {
		super(cards);
	}
}
