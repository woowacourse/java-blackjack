package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public interface State {

	State draw(Card card);

	State stay();

	double profitRate(Dealer dealer);

	boolean isRunning();

	Cards getCards();
}
