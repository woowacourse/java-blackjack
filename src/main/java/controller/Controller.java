package controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;
import domain.result.Result;
import view.InputView;
import view.OutputView;

public class Controller {

	public void run() {
		Deck deck = Deck.generateDeck();
		Dealer dealer = new Dealer(new Hand(deck.generateInitCards()));
		Players players = new Players(makePlayers(makeNames(), deck));
		printInitHands(dealer, players);

		if (!dealer.isBlackJack()) {
			drawForPlayers(deck, players);
			drawForDealer(deck, dealer, players);
		}

		printHandAndScore(dealer, players);
		OutputView.printResult(new Result(players.getResult(dealer)));
	}

	private List<Name> makeNames() {
		try {
			List<String> names = InputView.inputNames();
			return names.stream()
				.map(Name::new)
				.collect(Collectors.toList());
		} catch (IllegalArgumentException e) {
			OutputView.printMessage(e.getMessage());
			return makeNames();
		}
	}

	private List<Player> makePlayers(List<Name> names, Deck deck) {
		List<Player> players = names.stream()
			.map(
				name -> new Player(name, new Hand(deck.generateInitCards()),
					new Betting(InputView.inputBetting(name.getName()))))
			.collect(Collectors.toList());
		return players;
	}

	public void printInitHands(Dealer dealer, Players players) {
		OutputView.printInitMessage(players.showNames());
		OutputView.printOneHandForDealer(dealer.showName(), dealer.showHand());

		IntStream.range(0, players.getSize())
			.forEach(idx -> OutputView.printHand(players.showName(idx), players.showHand(idx)));
	}

	private void drawForPlayers(Deck deck, Players players) {
		IntStream.range(0, players.getSize())
			.forEach(idx -> askAndDrawForPlayer(deck, players, idx));
	}

	private void askAndDrawForPlayer(Deck deck, Players players, int idx) {
		boolean isKeepDraw = !(players.checkMaxScore(idx));

		while (isKeepDraw && InputView.askDraw(players.showName(idx))) {
			players.addCard(idx, deck.draw());
			OutputView.printHand(players.showName(idx), players.showHand(idx));
			isKeepDraw = checkMaxScoreOrBust(players, idx);
		}
	}

	private boolean checkMaxScoreOrBust(Players players, int idx) {
		if (players.checkMaxScore(idx) || players.checkBust(idx)) {
			return false;
		}
		return true;
	}

	private void drawForDealer(Deck deck, Dealer dealer, Players players) {
		if (players.checkAllBust()) {
			return;
		}

		while (!dealer.isEnoughCard()) {
			OutputView.printDealerDrawMessage();
			dealer.addCard(deck.draw());
		}
	}

	public void printHandAndScore(Dealer dealer, Players players) {
		OutputView.printHandAndScore(dealer.showName(), dealer.showHand(), dealer.getScore());

		IntStream.range(0, players.getSize())
			.forEach(idx -> OutputView.printHandAndScore(players.showName(idx), players.showHand(idx),
				players.showScore(idx)));
	}
}
