package controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;
import domain.result.EarningRate;
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
			drawForDealer(deck, dealer);
		}

		printHandAndScore(dealer, players);
		printResult(dealer, players);
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
		OutputView.printOneHandForDealer(dealer.getName(), dealer.getCards());
		players.forEach(player -> OutputView.printHand(player.getName(), player.getCards()));
	}

	private void drawForPlayers(Deck deck, Players players) {
		players.forEach(player -> askAndDrawForPlayer(deck, player));
	}

	private void askAndDrawForPlayer(Deck deck, Player player) {
		while (!player.isBlackJack() && !player.isBust()
			&& InputView.askDraw(player.getName())) {
			player.addCard(deck.draw());
			OutputView.printHand(player.getName(), player.getCards());
		}
	}

	private void drawForDealer(Deck deck, Dealer dealer) {
		while (!dealer.isEnoughCard()) {
			OutputView.printDealerDrawMessage();
			dealer.addCard(deck.draw());
		}
	}

	public void printHandAndScore(Dealer dealer, Players players) {
		OutputView.printHandAndScore(dealer.getName(), dealer.getCards(), dealer.getScore());
		players.forEach(
			player -> OutputView.printHandAndScore(player.getName(), player.getCards(), player.getScore()));
	}

	public static void printResult(Dealer dealer, Players players) {
		Result result = Result.of(dealer, players);
		OutputView.printEndMessage();
		OutputView.printResult(dealer.getName(), result.getDealerMoney());

		LinkedHashMap<Participant, EarningRate> playerResults = result.getPlayerResults();
		playerResults.entrySet()
			.forEach(entry -> OutputView.printResult(entry.getKey().getName(),
				(int)(entry.getKey().getBettingMoney() * entry.getValue().getEarningRate())));
	}
}
