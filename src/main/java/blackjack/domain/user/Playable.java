package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.Score;

import java.util.List;

public interface Playable {
	String DEALER_NAME = "딜러";
	int MINIMUM_NUMBER_TO_DEALER_STAY = 17;

	void receiveCard(Card card);

	void receiveCards(List<Card> cards);

	Score computeScore();

	boolean isBlackjack();

	boolean isBust();

	List<Card> getStartHand();

	List<Card> getHand();

	int countCards();

	Name getName();

	Boolean isWinner(Score dealerScore);

	boolean canReceiveCard();

	boolean isLoser(Score computeScore);
}
