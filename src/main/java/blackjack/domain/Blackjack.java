package blackjack.domain;

import java.util.List;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

public class Blackjack {
	private static final int STARTING_CARDS_COUNT = 2;

	private final Dealer dealer;
	private final List<Player> players;
	private final CardDeck cardDeck;

	private Blackjack(Dealer dealer, List<Player> players) {
		this.dealer = dealer;
		this.players = players;
		this.cardDeck = CardDeck.create();
	}

	public static Blackjack from(List<Player> players) {
		return new Blackjack(new Dealer(), players);
	}

	public void handOutStartingCards() {
		for (int i = 0; i < STARTING_CARDS_COUNT; i++) {
			handOutCardToAll();
		}
	}

	private void handOutCardToAll() {
		handOutCardTo(dealer);
		for (Player player : players) {
			handOutCardTo(player);
		}
	}

	public void handOutCardTo(Participant participant) {
		participant.receiveCard(this.cardDeck.pick());
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return List.copyOf(players);
	}
}
