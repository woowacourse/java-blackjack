package blackjack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.BlackJackManager;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

	private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";

	private final BlackJackManager manager;

	public BlackJackController() {
		List<String> names = InputView.askNames();
		validateDuplicationNames(names);
		this.manager = new BlackJackManager(createPlayers(names));
	}

	private void validateDuplicationNames(List<String> names) {
		int count = (int) names.stream()
			.distinct()
			.count();
		if (count != names.size()) {
			throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
		}
	}

	private List<Player> createPlayers(List<String> names) {
		return names.stream()
			.map(name -> new Player(name, InputView.askBet(name)))
			.collect(Collectors.toList());
	}

	public void play() {
		Dealer dealer = manager.getDealer();
		List<Player> players = manager.getPlayers();
		OutputView.printGamers(dealer, players);

		Deck deck = new Deck(Card.getCards());
		checkFirstHandOut(dealer, players, deck);
		askHitOrStay(deck);

		printFinalMessages(dealer, players);
	}

	private void checkFirstHandOut(Dealer dealer, List<Player> players, Deck deck) {
		manager.handOutFirst(deck);
		OutputView.printNameAndCards(dealer.getName(), manager.findDealerFirstCard());
		players.forEach(
			player -> OutputView.printNameAndCards(player.getName(), player.getCards()));
	}

	private void askHitOrStay(Deck deck) {
		askPlayersHitOrStay(deck);
		askDealerHitOrStay(deck);
	}

	private void askPlayersHitOrStay(Deck deck) {
		for (String name : manager.findPlayerNames()) {
			selectHitOrStay(name, deck);
		}
	}

	private void selectHitOrStay(String name, Deck deck) {
		while (!manager.checkPlayerBust(name) && InputView.askIfHit(name)) {
			manager.giveCardToPlayer(name, deck);
			OutputView.printNameAndCards(name, manager.findCardsOfPlayer(name));
		}
	}

	private void askDealerHitOrStay(Deck deck) {
		while (manager.checkDealerDrawPossible()) {
			manager.giveCardToDealer(deck);
		}
	}

	private void printFinalMessages(Dealer dealer, List<Player> players) {
		OutputView.printAdditionalDrawDealer(manager.findDealerHitCount());
		OutputView.printFinalCards(dealer, players);
		OutputView.printFinalResult(manager.createBettingResult());
	}
}
