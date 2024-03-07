package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.GameResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {
	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public Players createPlayers() {
		return new Players(inputView.readPlayerNames());
	}

	public Dealer createDealer() {
		List<Card> deck = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}

		return new Dealer(deck, new ArrayList<>());
	}

	public void dealInitCards(Dealer dealer, Players players) {
		for (Player player : players.getPlayers()) {
			player.receiveInitCards(dealer.dealInit());
		}
		dealer.receiveInitCards(dealer.dealInit());

		outputView.printInitCardStatus(dealer, players);
	}

	public void receiveAdditionalCard(Dealer dealer, Players players) {
		for (Player player : players.getPlayers()) {
			while (!player.isBust() && inputView.readHitOrStand(player).equals("y")) {
				player.receiveCard(dealer.dealCard());
				outputView.printCardStatus(player);
			}
		}
		while (dealer.tryHit()) {
			outputView.printDealerHitMessage();
		}
	}

	public void printTotalCardStatus(Dealer dealer, Players players) {
		outputView.printTotalCardStatus(dealer, players);
	}

	public void printGameResult(Dealer dealer, Players players) {
		Map<Player, GameResult> playerResults = new HashMap<>();

		for (Player player : players.getPlayers()) {
			playerResults.put(player, compareScore(dealer, player));
		}

		Map<GameResult, Long> dealerResult = getDealerResult(playerResults);
		outputView.printGameResult(dealerResult, playerResults);
	}

	private GameResult compareScore(Dealer dealer, Player player) {
		if (player.isBust() || player.getScore() <= dealer.getScore()) {
			return GameResult.LOSE;
		}
		return GameResult.WIN;
	}

	private Map<GameResult, Long> getDealerResult(Map<Player, GameResult> playerResults) {
		return playerResults.values().stream()
			.map(GameResult::reverse)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
}
