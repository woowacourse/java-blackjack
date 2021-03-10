package blackjack.domain.state;

import blackjack.domain.card.Cards;

public interface PlayerState {
	boolean isFinished();

	PlayerState keepContinue(boolean input);

	PlayerState checkStateWithNewCard(Cards cards);
}
