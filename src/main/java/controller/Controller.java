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

		//손패 출력
		OutputView.printInitMessage(names);
		OutputView.printParticipantStatus(dealer.showOneHand(), players.showHands());

		//initResult
		if (players.isExistBlackJack() || dealer.isBlackJack()) {
			Result blackjackResult = new Result(players.initCompare(dealer.isBlackJack()));
			OutputView.printResultTitle();
			OutputView.printDealerResult(blackjackResult.getDealerWinCount(), blackjackResult.getDealerDrawCount(),
				blackjackResult.getDealerLoseCount());
			for (String name : names) {
				OutputView.printPlayerResult(name, blackjackResult.getVersus(name).getResult());
			}
			return;
		}

		// 참가자들 draw
		for (String name : names) {
			while (askDraw(name)) {
				players.addCardByName(name, deck.draw());
				OutputView.printHand(players.showHandByName(name));
				if (players.isBlackJackByName(name)) {
					OutputView.printBlackJackMessage();
					break;
				}
				if (players.isBustByName(name)) {
					OutputView.printBustMessage();
					break;
				}
			}
		}

		//딜러 draw
		if (!players.isAllBust()) {
			while (!dealer.isEnoughCard()) {
				OutputView.printDealerDrawMessage();
				dealer.addCard(deck.draw());
			}
		}

		//최종 카드 & 베스트스코어 출력
		OutputView.printParticipantStatus(dealer.showHandAndBestScore(), players.showHandsAndBestScores());

		//최종결과 출력
		Result finalResult = new Result(players.finalCompare(dealer));
		OutputView.printResultTitle();
		OutputView.printDealerResult(
			finalResult.getDealerWinCount(),
			finalResult.getDealerDrawCount(),
			finalResult.getDealerLoseCount()
		);
		for (String name : names) {
			OutputView.printPlayerResult(name, finalResult.getVersus(name).getResult());
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
