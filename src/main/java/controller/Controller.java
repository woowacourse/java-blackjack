package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.card.Card;
import domain.card.Deck;
import domain.card.deckstrategy.GeneralGenerationDeckStrategy;
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
		List<Name> names = makeNames();

		Deck deck = Deck.from(new GeneralGenerationDeckStrategy());
		Dealer dealer = new Dealer(deck.generateInitCards());
		Players players = new Players(names, generateInitCardsForPlayers(names, deck));
		printInitHands(names, dealer, players);

		if (dealer.isMaxScore()) {
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

	private List<List<Card>> generateInitCardsForPlayers(List<Name> names, Deck deck) {
		List<List<Card>> initCardForPlayers = IntStream.range(0, names.size())
			.mapToObj(i -> deck.generateInitCards())
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
		boolean isKeepDraw = !(players.isMaxScoreByName(name));

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
		if (players.isMaxScoreByName(name)) {
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
