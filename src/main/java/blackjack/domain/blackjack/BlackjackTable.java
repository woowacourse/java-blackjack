package blackjack.domain.blackjack;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public class BlackjackTable {
	public static final int INITIAL_DEAL_NUMBER = 2;

	private final Deck deck;
	private final Dealer dealer;
	private final List<Player> players;

	public BlackjackTable(Deck deck, Dealer dealer, List<Player> players) {
		this.deck = deck;
		this.dealer = dealer;
		this.players = players;
	}

	public void dealInitialHand() {
		for (User user : collectUsers()) {
			user.draw(deck, INITIAL_DEAL_NUMBER);
		}
	}

	public List<User> collectUsers() {
		List<User> users = new ArrayList<>();
		users.add(dealer);
		users.addAll(players);
		return users;
	}

	public void playWith(UserDecisions userDecisions) {
		for (Player player : players) {
			drawCardFrom(player, userDecisions);
		}
		drawCardFrom(dealer, userDecisions);
	}

	private void drawCardFrom(Player player, UserDecisions userDecisions) {
		while (player.canDraw() && userDecisions.isHit(player)) {
			player.draw(deck);
			userDecisions.showHandStatus(player);
		}
	}

	private void drawCardFrom(Dealer dealer, UserDecisions userDecisions) {
		while (dealer.canDraw()) {
			dealer.draw(deck);
			userDecisions.showDealerHitStatus();
		}
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return players;
	}
}
