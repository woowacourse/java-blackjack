package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;

public interface PlayerState {
	boolean isFinished();

	PlayerState keepContinue(boolean input);

	PlayerState drawNewCard(Card card);

	int calculatePoint();

	Cards cards();

	double makeProfit(Dealer dealer, Money money);

	boolean isBust();

	boolean isBlackJack();
}
