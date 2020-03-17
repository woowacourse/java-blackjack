package domain;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;

import java.util.List;

public class Players {
	private final Dealer dealer;
	private final List<User> users;

	public Players(Dealer dealer, List<User> users) {
		this.dealer = dealer;
		this.users = users;
	}

	public void cardDraw(CardDeck cardDeck) {
		dealer.cardDraw(cardDeck.draw(Rull.FIRST_DRAW_COUNT));
		for (User user : users) {
			user.cardDraw(cardDeck.draw(Rull.FIRST_DRAW_COUNT));
		}
	}
}
