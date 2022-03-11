package controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.card.Card;
import domain.card.Deck;
import domain.card.InitCards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import domain.result.Result;
import view.InputView;
import view.OutputView;

public class Controller {

	private static final String INPUT_NAMES_SPLIT_DELIMITER = ",";
	private static final String NAME_DUPLICATE_ERROR_MESSAGE = "[Error] 이름은 중복일 수 없습니다.";

	public void run() {
		List<Name> names = inputNames();

		Deck deck = new Deck();
		Dealer dealer = new Dealer(new InitCards(deck).getInitCards());
		Players players = new Players(names, generateInitCardsForPlayers(names, deck));
		printInitHands(names, dealer, players);

		if (players.isExistBlackJack() || dealer.isBlackJack()) {
			printBlackJackResult(names, dealer, players);
			return;
		}

		drawForPlayers(names, deck, players);
		drawForDealer(deck, dealer, players);
		OutputView.printParticipantStatus(dealer.showHandAndBestScore(), players.showHandsAndBestScores());
		printFinalResult(names, dealer, players);
	}

	private List<Name> inputNames() {
		String inputNames = InputView.inputNames();
		List<Name> names = Arrays.stream(inputNames.split(INPUT_NAMES_SPLIT_DELIMITER))
			.map(String::trim)
			.map(Name::new)
			.collect(Collectors.toList());
		checkDuplicate(names);
		return names;
	}

	private void checkDuplicate(List<Name> names) {
		if (new HashSet<>(names).size() != names.size()) {
			throw new IllegalArgumentException(NAME_DUPLICATE_ERROR_MESSAGE);
		}
	}

	private List<List<Card>> generateInitCardsForPlayers(List<Name> names, Deck deck) {
		List<List<Card>> initCardForPlayers = IntStream.range(0, names.size())
			.mapToObj(i -> new InitCards(deck).getInitCards())
			.collect(Collectors.toList());
		return initCardForPlayers;
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
			OutputView.printPlayerResult(name.getName(), blackjackResult.getVersusOfPlayer(name).getResult());
		}
	}

	private void drawForPlayers(List<Name> names, Deck deck, Players players) {
		for (Name name : names) {
			askAndDrawForPlayer(deck, players, name);
		}
	}

	private void drawForDealer(Deck deck, Dealer dealer, Players players) {
		if (!players.isAllBust()) {
			drawForDealer(deck, dealer);
		}
	}

	private void askAndDrawForPlayer(Deck deck, Players players, Name name) {
		boolean isKeepDraw = true;

		while (isKeepDraw && askDraw(name.getName())) {
			players.addCardByName(name, deck.draw());
			OutputView.printHand(players.showHandByName(name));
			isKeepDraw = checkBlackJackOrBust(players, name);
		}
	}

	private boolean askDraw(String name) {
		String resultAskDraw = InputView.inputAskDraw(name);
		validateAskDraw(resultAskDraw);
		if (resultAskDraw.equals("y")) {
			return true;
		}

		return false;
	}

	private void validateAskDraw(String resultAsk) {
		if (!(resultAsk.equals("y") || resultAsk.equals("n"))) {
			throw new IllegalArgumentException();
		}
	}

	private boolean checkBlackJackOrBust(Players players, Name name) {
		if (players.isBlackJackByName(name)) {
			OutputView.printBlackJackMessage();
			return false;
		}
		if (players.isBustByName(name)) {
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
			OutputView.printPlayerResult(name.getName(), finalResult.getVersusOfPlayer(name).getResult());
		}
	}
}
