package blackjack.domain.participant;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import blackjack.domain.BettingAmount;
import blackjack.domain.DealerDrawChoice;
import blackjack.domain.Outcome;
import blackjack.domain.RedrawChoice;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

public class Roles {

	private static final String NO_PLAYER = "";

	private List<Participant> players;
	private Participant dealer;

	public void initDealer() {
		dealer = new Dealer(new Hand(), DealerDrawChoice::chooseDraw);
	}

	public Participant distributeCardToDealer(final Deck deck) {
		dealer.draw(deck, 1);
		final Participant dealerStatus = new Dealer(dealer.getHand(), DealerDrawChoice::chooseDraw);
		dealer.draw(deck, 1);
		return dealerStatus;
	}

	public List<Participant> distributeCardToPlayers(final Deck deck) {
		for (Participant player : players) {
			player.draw(deck, 2);
		}
		return players;
	}

	public Participant drawDealer(final Deck deck) {
		if (!dealer.canDraw()) {
			dealer.stopDraw();
			return dealer;
		}
		dealer.draw(deck, 1);
		return dealer;
	}

	public void joinPlayers(final Map<String, Integer> playersNameAndBattingAmount) {
		players = playersNameAndBattingAmount.entrySet()
			.stream()
			.map(player -> new Player(player.getKey(), new Hand(), new BettingAmount(player.getValue())))
			.collect(Collectors.toList());
	}

	public Participant drawPlayer(final Deck deck, final RedrawChoice answer, final String name) {
		final Participant currentPlayer = findPlayer(name);
		if (answer == RedrawChoice.NO) {
			currentPlayer.stopDraw();
			return currentPlayer;
		}
		currentPlayer.draw(deck, 1);
		return currentPlayer;
	}

	private Participant findPlayer(final String name) {
		return players.stream()
			.filter(player -> player.getName().equals(name))
			.findFirst()
			.orElseThrow(NoSuchElementException::new);
	}

	public String getCurrentPlayerName() {
		return players.stream()
			.filter(player -> player.canDraw() && player.wantDraw())
			.map(Participant::getName)
			.findFirst()
			.orElse(NO_PLAYER);
	}

	public List<Participant> calculatePlayerResult() {
		calculateBlackJackResult();
		for (Participant player : players) {
			final Outcome outcome = judge(player);
			player.distributeBettingAmount(outcome);
		}
		return players;
	}

	private void calculateBlackJackResult() {
		if (dealer.isBlackJack()) {
			return;
		}
		for (Participant player : players) {
			player.earnAmountByBlackJack();
		}
	}

	public Participant calculateDealerResult() {
		for (Participant player : players) {
			final Outcome outcome = judge(player);
			dealer.distributeBettingAmount(outcome.getCounterpartRoleOutcome(), player);
		}
		return dealer;
	}

	private Outcome judge(final Participant player) {
		return Outcome.of(player.calculateFinalScore(), dealer.calculateFinalScore());
	}
}
