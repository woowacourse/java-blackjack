package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.MatchResult;

public interface State {

	State draw(Card card);

	State stay();

	boolean isFinished();

	Cards getCards();

	MatchResult match(State state);
}
