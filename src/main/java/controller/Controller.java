package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.card.deckstrategy.GenerationStandardDeckStrategy;
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
		Deck deck = Deck.from(new GenerationStandardDeckStrategy());
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
		OutputView.printInitMessage(players.getNames().stream().map(Name::getName).collect(Collectors.toList()));
		OutputView.printOneHandForDealer(dealer.showName(), dealer.showHand());

		List<Player> playersInfo = players.getPlayerInfo();
		for (Player player : playersInfo) {
			OutputView.printHand(player.showName(), player.showHand());
		}
	}

	private void drawForPlayers(Deck deck, Players players) {
		players.getNames().stream()
			.forEach(name -> askAndDrawForPlayer(deck, players, name));
	}

	private void askAndDrawForPlayer(Deck deck, Players players, Name name) {
		boolean isKeepDraw = !(players.checkMaxScoreByName(name));

		while (isKeepDraw && InputView.askDraw(name.getName())) {
			players.addCardByName(name, deck.draw());
			OutputView.printHand(players.getPlayerInfoByName(name).showName(),
				players.getPlayerInfoByName(name).showHand());
			isKeepDraw = checkMaxScoreOrBust(players, name);
		}
	}

	private boolean checkMaxScoreOrBust(Players players, Name name) {
		if (players.checkMaxScoreByName(name) || players.checkBustByName(name)) {
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

		List<Player> playersInfo = players.getPlayerInfo();

		for (Player playerInfo : playersInfo) {
			OutputView.printHandAndScore(playerInfo.showName(), playerInfo.showHand(), playerInfo.getScore());
		}
	}
}
