package controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jetbrains.annotations.NotNull;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.InitCards;
import domain.Name;
import domain.Players;
import domain.Result;
import view.InputView;
import view.OutputView;

public class Controller {

	public void run() {
		List<Name> names = inputNames();

		Deck deck = new Deck();
		Dealer dealer = new Dealer(new InitCards(deck).getIntiCards());
		List<List<Card>> initCardsForPlayers = generateInitCardsForPlayers(names, deck);
		Players players = new Players(names, initCardsForPlayers);
		printInitHans(names, dealer, players);

		if (players.isExistBlackJack() || dealer.isBlackJack()) {
			printBlackJackResult(names, dealer, players);
			return;
		}

		askAndDrawForPlayers(names, deck, players);

		if (!players.isAllBust()) {
			drawForDealer(deck, dealer);
		}

		OutputView.printParticipantStatus(dealer.showHandAndBestScore(), players.showHandsAndBestScores());
		printFinalResult(names, dealer, players);
	}

	private void printFinalResult(List<Name> names, Dealer dealer, Players players) {
		Result finalResult = new Result(players.finalCompare(dealer));
		OutputView.printResultTitle();
		OutputView.printDealerResult(
			finalResult.getDealerWinCount(),
			finalResult.getDealerDrawCount(),
			finalResult.getDealerLoseCount()
		);
		for (Name name : names) {
			OutputView.printPlayerResult(name.getName(), finalResult.getVersus(name).getResult());
		}
	}

	private void drawForDealer(Deck deck, Dealer dealer) {
		while (!dealer.isEnoughCard()) {
			OutputView.printDealerDrawMessage();
			dealer.addCard(deck.draw());
		}
	}

	private void askAndDrawForPlayers(List<Name> names, Deck deck, Players players) {
		for (Name name : names) {
			aksAndDrawForPlayer(deck, players, name);
		}
	}

	private void aksAndDrawForPlayer(Deck deck, Players players, Name name) {
		boolean isKeepDraw = true;

		while (isKeepDraw && askDraw(name.getName())) {
			players.addCardByName(name, deck.draw());
			OutputView.printHand(players.showHandByName(name));
			isKeepDraw = checkBlackJackOrBust(players, name);
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

	private void printBlackJackResult(List<Name> names, Dealer dealer, Players players) {
		OutputView.printBlackJackResultTitle();
		Result blackjackResult = new Result(players.initCompare(dealer.isBlackJack()));
		OutputView.printResultTitle();
		OutputView.printDealerResult(blackjackResult.getDealerWinCount(), blackjackResult.getDealerDrawCount(),
			blackjackResult.getDealerLoseCount());
		for (Name name : names) {
			OutputView.printPlayerResult(name.getName(), blackjackResult.getVersus(name).getResult());
		}
	}

	private void printInitHans(List<Name> names, Dealer dealer, Players players) {
		OutputView.printInitMessage(names.stream().map(Name::getName).collect(Collectors.toList()));
		OutputView.printParticipantStatus(dealer.showOneHand(), players.showHands());
	}

	@NotNull
	private List<List<Card>> generateInitCardsForPlayers(List<Name> names, Deck deck) {
		List<List<Card>> initCardForPlayers = IntStream.range(0, names.size())
			.mapToObj(i -> new InitCards(deck).getIntiCards())
			.collect(Collectors.toList());
		return initCardForPlayers;
	}

	@NotNull
	private List<Name> inputNames() {
		String[] split = InputView.inputNames().split(",");
		List<Name> names = Arrays.stream(split).map(String::trim).map(Name::new).collect(Collectors.toList());
		checkDuplicate(names);
		return names;
	}

	private void checkDuplicate(List<Name> names) {
		if (new HashSet<>(names).size() != names.size()) {
			throw new IllegalArgumentException("[Error] 이름은 중복일 수 없습니다.");
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
}
