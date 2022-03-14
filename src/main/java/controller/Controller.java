package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.card.deckstrategy.GeneralGenerationDeckStrategy;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.Result;
import view.InputView;
import view.OutputView;

public class Controller {

	public void run() {
		List<Name> names = makeNames();

		Deck deck = Deck.from(new GeneralGenerationDeckStrategy());
		Dealer dealer = new Dealer(deck.generateInitCards());
		Players players = new Players(makePlayers(names, deck));
		printInitHands(names, dealer, players);

		if (dealer.isBlackJack()) {
			printBlackJackResult(names, dealer, players);
			return;
		}

		drawForPlayers(names, deck, players);
		drawForDealer(deck, dealer, players);
		OutputView.printParticipantStatus(dealer.showHandAndBestScore(), players.showHandsAndBestScores());
		printFinalResult(names, dealer, players);
	}

	private List<Name> makeNames() {
		try {
			List<String> names = InputView.inputNames();
			return names.stream()
				.map(Name::new)
				.collect(Collectors.toList());
		} catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
			return makeNames();
		}
	}

	private List<Player> makePlayers(List<Name> names, Deck deck) {
		List<Player> players = names.stream()
			.map(name -> new Player(name, deck.generateInitCards()))
			.collect(Collectors.toList());
		return players;
	}

	private void printInitHands(List<Name> names, Dealer dealer, Players players) {
		OutputView.printInitMessage(names.stream().map(Name::getName).collect(Collectors.toList()));
		OutputView.printParticipantStatus(dealer.showOneHand(), players.showHands());
	}

	private void printBlackJackResult(List<Name> names, Dealer dealer, Players players) {
		OutputView.printBlackJackResultTitle();
		Result blackjackResult = new Result(players.getResultAtBlackJack(dealer));
		OutputView.printResultTitle();
		OutputView.printDealerResult(blackjackResult.getDealerWinCount(), blackjackResult.getDealerDrawCount(),
			blackjackResult.getDealerLoseCount());

		for (Name name : names) {
			OutputView.printPlayerResult(name.getName(), blackjackResult.getResultOfPlayer(name).getResult());
		}
	}

	private void drawForPlayers(List<Name> names, Deck deck, Players players) {
		for (Name name : names) {
			askAndDrawForPlayer(deck, players, name);
		}
	}

	private void drawForDealer(Deck deck, Dealer dealer, Players players) {
		if (!players.checkAllBust()) {
			drawForDealer(deck, dealer);
		}
	}

	private void askAndDrawForPlayer(Deck deck, Players players, Name name) {
		boolean isKeepDraw = !(players.checkMaxScoreByName(name));

		while (isKeepDraw && InputView.askDraw(name.getName())) {
			players.addCardByName(name, deck.draw());
			OutputView.printHand(players.showHandByName(name));
			isKeepDraw = checkMaxScoreOrBust(players, name);
		}
	}

	private boolean checkMaxScoreOrBust(Players players, Name name) {
		if (players.checkMaxScoreByName(name)) {
			OutputView.printBlackJackMessage();
			return false;
		}
		if (players.checkBustByName(name)) {
			OutputView.printBustMessage();
			return false;
		}
		return true;
	}

	private void drawForDealer(Deck deck, Dealer dealer) {
		while (!dealer.isEnoughCard()) {
			OutputView.printDealerDrawMessage();
			dealer.addCard(deck.draw());
		}
	}

	private void printFinalResult(List<Name> names, Dealer dealer, Players players) {
		Result finalResult = new Result(players.getResultAtFinal(dealer));
		OutputView.printResultTitle();
		OutputView.printDealerResult(
			finalResult.getDealerWinCount(),
			finalResult.getDealerDrawCount(),
			finalResult.getDealerLoseCount()
		);

		for (Name name : names) {
			OutputView.printPlayerResult(name.getName(), finalResult.getResultOfPlayer(name).getResult());
		}
	}
}
