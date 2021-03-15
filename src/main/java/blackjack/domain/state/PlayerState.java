package blackjack.domain.state;

import java.util.stream.Stream;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;

public interface PlayerState {
	boolean isFinished();

	PlayerState keepContinue(boolean input);

	PlayerState drawNewCard(Card card);

	int calculatePoint();

	double makeProfit(Dealer dealer, Money money);

	boolean isBust();

	boolean isBlackJack();

	Stream<Card> getCardStream();
}
