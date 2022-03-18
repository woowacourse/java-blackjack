package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public interface State {

	State draw(Card card);

	State stay();

	Money calculateProfit(Money money, Dealer dealer);

	boolean isRunning();

	Cards getCards();
}
