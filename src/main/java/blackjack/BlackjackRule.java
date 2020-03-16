package blackjack;

import blackjack.card.domain.CardDeck;
import blackjack.player.domain.Players;

public class BlackjackRule {
	public static final int STARTING_CARD_COUNT = 2;

	public static void drawStartingCard(Players players, CardDeck cardDeck) {
		for (int i = 0; i < STARTING_CARD_COUNT; i++) {
			players.drawCard(cardDeck);
		}
	}
}
