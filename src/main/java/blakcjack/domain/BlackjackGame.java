package blakcjack.domain;

import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackjackGame {
	private final Deck deck;
	private final Participant dealer;
	private final List<Participant> players = new ArrayList<>();

	public BlackjackGame(final Deck deck, final List<String> names) {
		this.deck = deck;
		this.dealer = new Dealer();
		for (String name : names) {
			players.add(new Player(new Name(name)));
		}
	}

	public List<Participant> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public void distributeOneCard(final Participant player) {
		player.receiveCard(deck.drawCard());
	}

	public void initializeHands() {
		for (Participant player : players) {
			distributeOneCard(player);
			distributeOneCard(player);
		}
		distributeOneCard(dealer);
	}

	public Dealer getDealer() {
		return (Dealer) dealer;
	}
}
