package blackjack.domain.role;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.state.State;
import java.util.List;
import java.util.function.Supplier;

public final class Dealer extends Role {

	public static final int CAN_NOT_DRAW_STANDARD = 17;
	public static final int CAN_DRAW_STANDARD = 16;

	private static final int openCard = 0;
	private static final String DEALER_NAME = "딜러";
	private static final String DEALER_SETTLE_ERROR = "딜러는 승부를 겨룰 수 없습니다.";

	private final Supplier<Boolean> drawChoice;

	public Dealer(final State state, final Supplier<Boolean> drawChoice) {
		super(DEALER_NAME, state);
		this.drawChoice = drawChoice;
	}

	@Override
	public void draw(final Deck deck) {
		if (state.isFinished()) {
			return;
		}
		int score = state.getScore();
		if (score <= CAN_DRAW_STANDARD) {
			state = state.draw(deck.draw());
		}
		if (!getCards().hasAce()) {
			return;
		}
		if (drawChoice.get()) {
			state = state.draw(deck.draw());
		}
	}

	@Override
	public List<Card> openCards() {
		List<Card> cards = state.getCards().getCards();
		return List.of(cards.get(openCard));
	}

	@Override
	public Money settle(Role dealer, Money bettingMoney) {
		throw new IllegalStateException(DEALER_SETTLE_ERROR);
	}
}
