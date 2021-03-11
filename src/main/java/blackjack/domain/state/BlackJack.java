package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class BlackJack extends Finished {
	public BlackJack(Cards cards) {
		super(cards);
	}

	@Override
	public boolean isBurst() {
		return false;
	}
}
