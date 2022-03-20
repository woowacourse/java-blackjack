package blackjack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.BlackJackReferee;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BettingResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

	private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";

	private final Gamers gamers;
	private final Deck deck;

	public BlackJackController() {
		List<String> names = InputView.askNames();
		validateDuplicationNames(names);
		this.gamers = new Gamers(createPlayers(names));
		this.deck = new Deck(Card.getCards());
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
		Dealer dealer = gamers.getDealer();
		List<Player> players = gamers.getPlayers();
		OutputView.printGamers(dealer, players);

		checkFirstHandOut(dealer, players);
		askHitOrStay();

		BettingResult result = BlackJackReferee.create(dealer, players);
		printFinalMessages(dealer, players, result);
	}

	private void checkFirstHandOut(Dealer dealer, List<Player> players) {
		gamers.handOutFirst(deck);
		OutputView.printNameAndCards(dealer.getName(), gamers.findDealerFirstCard());
		players.forEach(
			player -> OutputView.printNameAndCards(player.getName(), player.getCards()));
	}

	private void askHitOrStay() {
		askPlayersHitOrStay();
		askDealerHitOrStay();
	}

	private void askPlayersHitOrStay() {
		for (String name : gamers.findPlayerNames()) {
			selectHitOrStay(name);
		}
	}

	private void selectHitOrStay(String name) {
		while (!gamers.checkPlayerBust(name) && InputView.askIfHit(name)) {
			gamers.giveCardToPlayer(name, deck);
			OutputView.printNameAndCards(name, gamers.findCardsOfPlayer(name));
		}
	}

	private void askDealerHitOrStay() {
		while (gamers.checkDealerDrawPossible()) {
			gamers.giveCardToDealer(deck);
		}
	}

	private void printFinalMessages(Dealer dealer, List<Player> players, BettingResult result) {
		OutputView.printAdditionalDrawDealer(gamers.findDealerHitCount());
		OutputView.printFinalCards(dealer, players);
		OutputView.printFinalResult(result);
	}
}
