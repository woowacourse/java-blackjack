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

	public List<Participant> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public Dealer getDealer() {
		return (Dealer) dealer;
	}

	public OutcomeStatistics judgeOutcome() {
		final Map<String, Outcome> playersOutcome = new LinkedHashMap<>();
		final Map<Outcome, Integer> dealerOutcome = new HashMap<>();
		initializeDealerOutcome(dealerOutcome);

		for (final Participant player : players) {
			final Outcome playerOutcome = Outcome.of((Player) player, (Dealer) dealer);
			playersOutcome.put(player.getName(), playerOutcome);
			updateDealerOutcome(dealerOutcome, playerOutcome);
		}
		return new OutcomeStatistics(dealerOutcome, playersOutcome);
	}

	private void updateDealerOutcome(final Map<Outcome, Integer> dealerOutcome, final Outcome playerOutcome) {
		dealerOutcome.computeIfPresent(playerOutcome.getDealerOutcome(), (outcome, count) -> count + 1);
	}

	private void initializeDealerOutcome(final Map<Outcome, Integer> dealerOutcome) {
		dealerOutcome.put(Outcome.WIN, 0);
		dealerOutcome.put(Outcome.DRAW, 0);
		dealerOutcome.put(Outcome.LOSE, 0);
	}
}
