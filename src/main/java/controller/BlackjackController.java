package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import view.InputView;
import view.OutputView;

public class BlackjackController {

	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackController(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void run() {
		final List<Player> players = inputPlayers();
		final Dealer dealer = new Dealer();
		final Deck deck = Deck.createShuffledDeck();
		handOutCards(players, dealer, deck);

	}

	private List<Player> inputPlayers() {
		final List<String> playerNames = inputView.readPlayerNames();
		return playerNames.stream()
			.map(Player::new)
			.collect(Collectors.toList());
	}

	private void handOutCards(final List<Player> players, final Dealer dealer, final Deck deck) {
		players.forEach(player -> {
			player.pickCard(deck);
			player.pickCard(deck);
		});
		dealer.pickCard(deck);
		dealer.pickCard(deck);
	}

	private void outputHandOut(final List<Player> players, final Dealer dealer) {
		List<String> names = players.stream()
			.map(Player::getName)
			.collect(Collectors.toList());
		outputView.printHandOutIntroduce(names);
	}
}
