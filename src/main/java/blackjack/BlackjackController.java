package blackjack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.domain.GameResult;
import blackjack.domain.card.Deck;
import blackjack.domain.card.UnShuffledDeckGenerator;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

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

	public Deck createDeck() {
		UnShuffledDeckGenerator deckGenerator = UnShuffledDeckGenerator.getInstance();
		return deckGenerator.generate();
	}

	public Dealer createDealer(Deck deck) {
		return Dealer.newInstance(deck);
	}

	public void startGame(Dealer dealer, Players players) {
		dealInitCards(dealer, players);
		receiveAdditionalCard(dealer, players);
	}

	private void dealInitCards(Dealer dealer, Players players) {
		for (Player player : players.getPlayers()) {
			player.receiveInitCards(dealer.dealInit());
		}
		dealer.receiveInitCards(dealer.dealInit());

		outputView.printInitCardStatus(dealer, players);
	}

	private void receiveAdditionalCard(Dealer dealer, Players players) {
		for (Player player : players.getPlayers()) {
			receivePlayerAdditionalCard(dealer, player);
		}
		receiveDealerAdditionalCard(dealer);
	}

	private void receivePlayerAdditionalCard(Dealer dealer, Player player) {
		while (!player.isBust() && isPlayerInputHit(player)) {
			player.receiveCard(dealer.dealCard());
			outputView.printCardHandStatus(player);
		}
	}

	private boolean isPlayerInputHit(Player player) {
		return inputView.readHitOrStand(player).equals("y");
	}

	private void receiveDealerAdditionalCard(Dealer dealer) {
		while (dealer.tryHit()) {
			outputView.printDealerHitMessage();
		}
	}

	public void printResult(Dealer dealer, Players players) {
		printTotalCardStatus(dealer, players);
		printGameResult(dealer, players);
	}

	private void printTotalCardStatus(Dealer dealer, Players players) {
		outputView.printTotalCardHandStatus(dealer, players);
	}

	private void printGameResult(Dealer dealer, Players players) {
		Map<Player, GameResult> playerResults = new HashMap<>();
		for (Player player : players.getPlayers()) {
			playerResults.put(player, compareScore(dealer, player));
		}

		Map<GameResult, Long> dealerResult = getDealerResult(playerResults);
		outputView.printGameResult(dealerResult, playerResults);
	}

	private GameResult compareScore(Dealer dealer, Player player) {
		return GameResult.of(dealer, player);
	}

	private Map<GameResult, Long> getDealerResult(Map<Player, GameResult> playerResults) {
		return playerResults.values().stream()
			.map(GameResult::reverse)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
}
