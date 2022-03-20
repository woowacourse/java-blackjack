package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import blackjack.domain.BettingAmount;
import blackjack.domain.RedrawChoice;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;

public class Participants {

	private static final String NO_PLAYER = "";

	private List<Participant> players;
	private Participant dealer;

	public void initDealer() {
		dealer = new Dealer(new Hand());
	}

	public Participant distributeCardToDealer(final Deck deck) {
		dealer.draw(deck, 1);
		final Participant dealerStatus = new Dealer(dealer.getHand());
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
		while (dealer.canDraw()) {
			dealer.draw(deck, 1);
		}
		dealer.stopDraw();
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

	public List<Participant> calculateResult() {
		calculateBlackJackResult();
		for (Participant player : players) {
			player.getBattingAmountFrom(dealer);
			dealer.getBattingAmountFrom(player);
		}
		return combineResult();
	}

	private void calculateBlackJackResult() {
		if (dealer.isBlackJack()) {
			return;
		}
		for (Participant player : players) {
			player.earnAmountByBlackJack();
		}
	}

	private List<Participant> combineResult() {
		final List<Participant> results = new ArrayList<>();
		results.add(dealer);
		results.addAll(players);
		return results;
	}

}
