package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.ResultScore;

public interface BlackjackParticipant {
	void hit(Deck deck);

	void hit(Deck deck, int hitNumber);

	boolean canDraw();

	ResultScore calculateResultScore();

	String getName();

	List<Card> getHand();

	List<Card> getInitialDealtHand();

	int getScore();
}
