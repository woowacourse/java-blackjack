package blakcjack.domain;

import blakcjack.domain.card.Deck;
import blakcjack.domain.name.Name;
import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Participant;
import blakcjack.domain.participant.Player;

import java.util.*;

public class BlackjackGame {
	public static final String DUPLICATE_NAME_ERROR = "중복된 이름이 입력 되었습니다.";
	private final Deck deck;
	private final Participant dealer;
	private final List<Participant> players = new ArrayList<>();

	public BlackjackGame(final Deck deck, final List<String> names) {
		this.deck = deck;
		this.dealer = new Dealer();

		Set<String> nameGroup = new HashSet<>(names);
		if (nameGroup.size() != names.size()) {
			throw new IllegalArgumentException(DUPLICATE_NAME_ERROR);
		}

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
		distributeOneCard(dealer);
	}

	public Dealer getDealer() {
		return (Dealer) dealer;
	}
}
