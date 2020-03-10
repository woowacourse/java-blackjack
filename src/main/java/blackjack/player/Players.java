package blackjack.player;

import java.util.List;

import blackjack.player.card.CardFactory;

public class Players {
	private final List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
	}

	public void receiveCards(CardFactory cardFactory) {
		for (Player player : players) {
			player.addCard(cardFactory.drawCard());
		}
	}
}
