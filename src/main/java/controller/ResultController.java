package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import domain.result.Result;
import view.OutputView;

public class ResultController {
	public ResultController() {
	}

	private List<String> convertNamesToString(List<Name> names) {
		return names.stream().map(Name::getName).collect(Collectors.toList());
	}

	private List<String> convertCardsToString(List<Card> cards) {
		return cards.stream()
			.map(Card::getCardInfo)
			.collect(Collectors.toList());
	}

	private List<List<String>> convertCardsListToString(List<List<Card>> cardsList) {
		return cardsList.stream()
			.map(cards -> convertCardsToString(cards))
			.collect(Collectors.toList());
	}

	public void printInitHands(Dealer dealer, Players players) {
		OutputView.printInitMessage(convertNamesToString(players.getNames()));
		OutputView.printHand(
			dealer.getName().getName(),
			Arrays.asList(dealer.getOneHand().getCardInfo())
		);

		List<String> playerNames = convertNamesToString(players.getNames());
		List<List<String>> playerCards = convertCardsListToString(players.getCardsOfAll());

		for (int i = 0; i < playerNames.size(); i++) {
			OutputView.printHand(playerNames.get(i), playerCards.get(i));
		}
	}

	public void printBlackJackResult(Dealer dealer, Players players) {
		OutputView.printBlackJackResultTitle();
		Result blackjackResult = new Result(players.getResultAtBlackJack(dealer));

		OutputView.printResultTitle();
		OutputView.printDealerResult(
			blackjackResult.getDealerWinCount(),
			blackjackResult.getDealerDrawCount(),
			blackjackResult.getDealerLoseCount()
		);

		players.getNames().stream()
			.forEach(name -> OutputView.printPlayerResult(name.getName(),
				blackjackResult.getResultOfPlayer(name).getResult()));
	}

	public void printHandAndResult(Dealer dealer, Players players) {
		OutputView.printHandAndScore(
			dealer.getName().getName(),
			convertCardsToString(dealer.getHand()),
			dealer.getBestScore()
		);

		List<String> names = convertNamesToString(players.getNames());
		List<List<String>> cards = convertCardsListToString(players.getCardsOfAll());
		List<Integer> scores = players.getScores();

		for (int i = 0; i < names.size(); i++) {
			OutputView.printHandAndScore(names.get(i), cards.get(i), scores.get(i));
		}
	}

	public void printFinalResult(Dealer dealer, Players players) {
		Result finalResult = new Result(players.getResultAtFinal(dealer));

		OutputView.printResultTitle();
		OutputView.printDealerResult(
			finalResult.getDealerWinCount(),
			finalResult.getDealerDrawCount(),
			finalResult.getDealerLoseCount()
		);

		players.getNames().stream()
			.forEach(
				name -> OutputView.printPlayerResult(name.getName(), finalResult.getResultOfPlayer(name).getResult()));
	}
}
