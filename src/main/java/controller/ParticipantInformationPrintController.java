package controller;

import java.util.List;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.ParticipantInfo;
import domain.participant.Players;
import domain.result.Result;
import view.OutputView;

public class ParticipantInformationPrintController {
	public ParticipantInformationPrintController() {
	}

	public void printHand(Players players, Name name) {
		OutputView.printHand(players.getPlayerInfoByName(name));
	}

	public void printInitHands(Dealer dealer, Players players) {
		OutputView.printInitMessage(players.getNames());
		OutputView.printOneHandForDealer(new ParticipantInfo(dealer));

		List<ParticipantInfo> playersInfo = players.getPlayerInfo();
		for (int i = 0; i < playersInfo.size(); i++) {
			OutputView.printHand(playersInfo.get(i));
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
			new ParticipantInfo(dealer),
			dealer.getBestScore()
		);

		List<ParticipantInfo> playersInfo = players.getPlayerInfo();
		List<Integer> scores = players.getScores();

		for (int i = 0; i < playersInfo.size(); i++) {
			OutputView.printHandAndScore(playersInfo.get(i), scores.get(i));
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
