package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.Money;

public interface State {

	State draw(final Card card);

	Stay stay();

	int getScore();

	Cards getCards();

	boolean isFinished();

	Money profit(Cards cards, Money money);
}
