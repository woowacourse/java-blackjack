package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.Score;

import java.util.List;

public interface Player {
	void giveCard(Card card);

	void giveCards(List<Card> cards);

	Score getScore();

	boolean isBust();

	List<Card> getHand();

	int countCards();

	String getName();

	Boolean isWinner(Score dealerScore);

	boolean isNotBust();

	boolean isDealer();
}
