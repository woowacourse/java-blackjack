package blackjack.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.domain.GameResult;
import blackjack.domain.card.Deck;
import blackjack.domain.card.ShuffledDeckGenerator;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.dto.gamer.PlayerInfo;
import blackjack.dto.gamer.PlayerInfos;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.command.Command;

public class BlackjackGameController {
	private final InputView inputView;
	private final OutputView outputView;

	public BlackjackGameController(final InputView inputView, final OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void start() {
		Players players = Players.fromNames(inputView.readPlayerNames());
		Dealer dealer = new Dealer();
		Deck deck = createDeck();

		dealInitCards(deck, dealer, players);
		receiveAdditionalCard(deck, dealer, players);
		printResult(dealer, players);
	}

	private Deck createDeck() {
		ShuffledDeckGenerator deckGenerator = ShuffledDeckGenerator.getInstance();
		return deckGenerator.generate();
	}

	private void dealInitCards(final Deck deck, final Dealer dealer, final Players players) {
		for (Player player : players.getPlayers()) {
			player.receiveInitCards(deck.drawInitCards());
		}
		dealer.receiveInitCards(deck.drawInitCards());

		outputView.printInitCardStatus(dealer.getFirstCard(), PlayerInfos.from(players));
	}

	private void receiveAdditionalCard(final Deck deck, final Dealer dealer, final Players players) {
		for (Player player : players.getPlayers()) {
			receivePlayerAdditionalCard(deck, player);
		}
		receiveDealerAdditionalCard(deck, dealer);
	}

	private void receivePlayerAdditionalCard(final Deck deck, final Player player) {
		while (!player.isBust() && isPlayerInputHit(player)) {
			player.receiveCard(deck.drawCard());
			outputView.printCardsStatus(PlayerInfo.from(player));
		}
	}

	private boolean isPlayerInputHit(final Player player) {
		return inputView.readHitOrStand(player).equals(Command.YES);
	}

	private void receiveDealerAdditionalCard(final Deck deck, final Dealer dealer) {
		while (dealer.hasHitScore()) {
			dealer.receiveCard(deck.drawCard());
			outputView.printDealerHitMessage();
		}
	}

	public void printResult(final Dealer dealer, final Players players) {
		printTotalCardStatus(dealer, players);
		printGameResult(dealer, players);
	}

	private void printTotalCardStatus(final Dealer dealer, final Players players) {
		outputView.printTotalCardsStatus(dealer, players);
	}

	private void printGameResult(final Dealer dealer, final Players players) {
		Map<Player, GameResult> playersResult = getPlayersResult(dealer,
			players);
		Map<GameResult, Long> dealerResult = getDealerResult(playersResult);

		outputView.printGameResult(dealerResult, playersResult);
	}

	private Map<Player, GameResult> getPlayersResult(Dealer dealer, Players players) {
		Map<Player, GameResult> playerResults = new HashMap<>();
		for (Player player : players.getPlayers()) {
			playerResults.put(player, compareScore(dealer, player));
		}
		return playerResults;
	}

	private GameResult compareScore(final Dealer dealer, final Player player) {
		return GameResult.of(dealer, player);
	}

	private Map<GameResult, Long> getDealerResult(Map<Player, GameResult> playerResults) {
		return playerResults.values().stream()
			.map(GameResult::reverse)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
}
