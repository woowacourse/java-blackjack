package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public interface BlackjackParticipant {
	void hit(Deck deck, int hitNumber);

	void hit(Deck deck);

	boolean canDraw();

	String getName();

	List<Card> getHand();

	List<Card> getInitialDealtHand();

	int getScore();

	int getBustHandledScore();
}
