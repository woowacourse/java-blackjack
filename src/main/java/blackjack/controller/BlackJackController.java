package blackjack.controller;

import static java.util.stream.Collectors.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.result.BettingResult;
import blackjack.domain.result.CardResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

	private static final String DUPLICATION_NAME_ERROR = "중복된 이름이 존재합니다.";

	private final Deck deck;
	private final Dealer dealer;
	private final Players players;

	public BlackJackController() {
		List<String> names = InputView.askNames();
		this.players = new Players(createPlayers(names));
		this.dealer = new Dealer();
		this.deck = new Deck(Card.getCards());
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
		OutputView.printGamers(dealer.getName(), players.findNames());
		checkFirstHandOut();
		askHitOrStay();
		printFinalMessages();
	}

	private void checkFirstHandOut() {
		handOutFirst();
		OutputView.printNameAndCards(dealer.getName(), dealer.openFirstCard());
		players.findNames().forEach(
			playerName -> OutputView.printNameAndCards(playerName, players.findCards(playerName)));
	}

	private void handOutFirst() {
		dealer.addCard(deck.draw());
		dealer.addCard(deck.draw());
		players.handOutFirst(deck);
	}

	private void askHitOrStay() {
		askPlayersHitOrStay();
		askDealerHitOrStay();
	}

	private void askPlayersHitOrStay() {
		for (String name : players.findNames()) {
			selectHitOrStay(name, deck);
		}
	}

	private void selectHitOrStay(String name, Deck deck) {
		while (!players.checkBust(name) && InputView.askIfHit(name)) {
			players.handOut(name, deck);
			OutputView.printNameAndCards(name, players.findCards(name));
		}
	}

	private void askDealerHitOrStay() {
		while (dealer.isDrawable()) {
			dealer.addCard(deck.draw());
		}
	}

	private void printFinalMessages() {
		OutputView.printAdditionalDrawDealer(dealer.findHitCount());
		OutputView.printFinalCards(new CardResult(dealer), createPlayerCardResults());
		OutputView.printFinalResult(createBettingResult());
	}

	private List<CardResult> createPlayerCardResults() {
		return players.getValue().stream()
			.map(CardResult::new)
			.collect(Collectors.toList());
	}

	private BettingResult createBettingResult() {
		Map<String, Integer> result = players.findNames().stream()
			.collect(
				toMap(playerName -> playerName,
					playerName -> players.calculatePlayerEarning(playerName, dealer)));
		return new BettingResult(result);
	}
}
