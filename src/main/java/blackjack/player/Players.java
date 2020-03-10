package blackjack.player;

import java.util.List;

import blackjack.player.card.CardFactory;

public class Players {
	private final List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
	}

	public void initCards(CardFactory cardFactory) {
		for (int i = 0; i < 2; i++) {
			players.forEach(player -> player.addCard(cardFactory.drawCard()));
		}
	}
}
