package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface PlayerState {
	boolean isFinished();

	PlayerState keepContinue(boolean input);

	PlayerState drawNewCard(Card card);

	int calculatePoint();

	Cards cards();

	boolean isBurst();
}
