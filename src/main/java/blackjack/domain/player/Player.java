package blackjack.domain.player;

import java.util.List;

import blackjack.domain.card.Card;

public interface Player {

	int PLAYER_SETTING_CARD_SIZE = 2;

	void receiveCard(final Card card);

	List<Card> openCards();

	List<Card> showCards();

	int calculateResult();

	boolean isReceivable();
}
