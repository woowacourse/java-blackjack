package blackjack.controller;

import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.BlackJackManager;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

	private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";

	private final BlackJackManager manager;

	public BlackJackController() {
		List<String> names = InputView.askNames();
		this.manager = new BlackJackManager(createPlayers(names));
	}

	private Map<String, Integer> createPlayers(List<String> names) {
		return names.stream()
			.collect(toMap(name -> name, InputView::askBet,
				this::validateDuplicateName,
				LinkedHashMap::new));
	}

	private int validateDuplicateName(int name, int duplicateName) {
		throw new IllegalArgumentException(DUPLICATION_NAME_ERROR);
	}

	public void play() {
		String dealer = manager.findDealerName();
		List<String> players = manager.findPlayerNames();
		OutputView.printGamers(dealer, players);

		Deck deck = new Deck(Card.getCards());
		checkFirstHandOut(dealer, players, deck);
		askHitOrStay(deck);

		printFinalMessages();
	}

	private void checkFirstHandOut(String dealer, List<String> players, Deck deck) {
		manager.handOutFirst(deck);
		OutputView.printNameAndCards(dealer, manager.findDealerFirstCard());
		players.forEach(
			player -> OutputView.printNameAndCards(player, manager.findCardsOfPlayer(player)));
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

	private void printFinalMessages() {
		OutputView.printAdditionalDrawDealer(manager.findDealerHitCount());
		OutputView.printFinalCards(manager.createDealerResult(), manager.createPlayerResults());
		OutputView.printFinalResult(manager.createBettingResult());
	}
}
