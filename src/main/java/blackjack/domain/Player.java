package blackjack.domain;

import java.util.List;

public interface Player {

	int PLAYER_ALREADY_CARD_SIZE = 2;

	void receiveCard(final Card card);

	List<Card> openCards();

	List<Card> showCards();

	int calculateResult();

	boolean isReceivable();
}
