package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.card.Hand;
import domain.card.deckstrategy.GeneralGenerationDeckStrategy;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.ParticipantInfo;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.Result;
import view.InputView;
import view.OutputView;

public class Controller {

	public void run() {
		Deck deck = Deck.from(new GeneralGenerationDeckStrategy());
		Dealer dealer = new Dealer(new Hand(deck.generateInitCards()));
		Players players = new Players(makePlayers(makeNames(), deck));
		printInitHands(dealer, players);

		if (dealer.isBlackJack()) {
			printBlackJackResult(dealer, players);
			return;
		}

		drawForPlayers(deck, players);
		drawForDealer(deck, dealer, players);

		printHandAndResult(dealer, players);
		printFinalResult(dealer, players);
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
			.map(name -> new Player(name, new Hand(deck.generateInitCards())))
			.collect(Collectors.toList());
		return players;
	}

	private void drawForPlayers(Deck deck, Players players) {
		players.getNames().stream()
			.forEach(name -> askAndDrawForPlayer(deck, players, name));
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
			OutputView.printHand(players.getPlayerInfoByName(name));
			isKeepDraw = checkMaxScoreOrBust(players, name);
		}
	}

	private boolean checkMaxScoreOrBust(Players players, Name name) {
		if (players.checkMaxScoreByName(name) || players.checkBustByName(name)) {
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

	public void printInitHands(Dealer dealer, Players players) {
		OutputView.printInitMessage(players.getNames());
		OutputView.printOneHandForDealer(new ParticipantInfo(dealer));

		List<ParticipantInfo> playersInfo = players.getPlayerInfo();
		for (int i = 0; i < playersInfo.size(); i++) {
			OutputView.printHand(playersInfo.get(i));
		}
	}

	public void printBlackJackResult(Dealer dealer, Players players) {
		Result blackjackResult = new Result(players.getResultAtBlackJack(dealer));

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
		OutputView.printHandAndScore(new ParticipantInfo(dealer));

		List<ParticipantInfo> playersInfo = players.getPlayerInfo();

		for (int i = 0; i < playersInfo.size(); i++) {
			OutputView.printHandAndScore(playersInfo.get(i));
		}
	}

	public void printFinalResult(Dealer dealer, Players players) {
		Result finalResult = new Result(players.getResultAtFinal(dealer));

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
