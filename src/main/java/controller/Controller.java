package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.InitCards;
import domain.Players;
import domain.Result;
import view.InputView;
import view.OutputView;

public class Controller {

	public void run() {
		String[] split = InputView.inputNames().split(",");
		List<String> names = Arrays.stream(split).map(String::trim).collect(Collectors.toList());

		Deck deck = new Deck();
		Dealer dealer = new Dealer(new InitCards(deck).getIntiCards());
		List<List<Card>> initCardForPlayers = IntStream.range(0, names.size())
			.mapToObj(i -> new InitCards(deck).getIntiCards())
			.collect(Collectors.toList());
		Players players = new Players(names, initCardForPlayers);

		OutputView.printHands(names, dealer.showOneHand(), players.showHands());

		if (players.isExistBlackJack() || dealer.isBlackJack()) {
			Result initResult = new Result(players.initCompare(dealer.isBlackJack()));
			OutputView.printResultTitle();
			OutputView.printDealerResult(initResult.getDealerWinCount(), initResult.getDealerDrawCount(),
				initResult.getDealerLoseCount());
			for (String name : names) {
				OutputView.printPlayerResult(name, initResult.getVersus(name).getResult());
			}
			return;
		}

		for (String name : names) {
			while (askDraw(name)) {
				players.addCardByName(name, deck.draw());
				OutputView.printHand(players.showHandByName(name));
				if (players.isBlackJackByName(name)) {
					OutputView.printBlackJackMessage();
					break;
				}
				if (players.isBurstByName(name)) {
					OutputView.printBurstMessage();
					break;
				}
			}
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
