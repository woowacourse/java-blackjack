package blackjack.domain.role;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.state.State;
import java.util.List;

public final class Player extends Role {

	private final Money bettingMoney;

	public Player(final String name, final State state, final Money bettingMoney) {
		super(name, state);
		this.bettingMoney = bettingMoney;
	}

	@Override
	public void draw(final Deck deck) {
		state = state.draw(deck.draw());
	}

	@Override
	public List<Card> openCards() {
		return state.getCards().getCards();
	}

	@Override
	public Money settle(Role dealer) {
		return state.profit(dealer.getCards(), bettingMoney);
	}
}
