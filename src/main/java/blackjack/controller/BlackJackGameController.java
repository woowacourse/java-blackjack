package blackjack.controller;

import blackjack.domain.card.DeckStrategy;
import blackjack.domain.result.BettingBox;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGameController {

	private final InputView inputView;
	private final OutputView outputView;
	private final DeckStrategy deck;

	public BlackJackGameController(InputView inputView, OutputView outputView, DeckStrategy deckStrategy) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.deck = deckStrategy;
	}

	public void gameStart() {
		Players players = generatePlayers();
		Dealer dealer = new Dealer(deck);

		printInitialCards(players, dealer);

		progressPlayerTurn(players, deck);
		progressDealerTurn(dealer, deck);
		makeResult(players, dealer);
	}

	private Players generatePlayers() {
		try {
			Players players = new Players(inputView.inputPlayerNames(), deck);
			return players;
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			return generatePlayers();
		}
	}

	private void printInitialCards(Players players, Dealer dealer) {
		outputView.displayFirstDistribution(players.getPlayers());
		outputView.displayOneCard(dealer.getCards().get(0));
		for (Player player : players.getPlayers()){
			outputView.displayAllCard(player.getName(), player.getCards());
		}
	}

	private void progressPlayerTurn(Players players, DeckStrategy deck) {
		for (Player player : players.getPlayers()) {
			progressOnePlayer(deck, player);
		}
	}

	private void progressOnePlayer(DeckStrategy deck, Player player) {
		while (player.isRunning() && decidePlayerHit(player)) {
			player.addCard(deck.distributeCard());
		}
	}

	private boolean decidePlayerHit(Player player) {
		if (!inputView.isHitDecision(player.getName())) {
			player.stay();
			return false;
		}
		return true;
	}

	private void progressDealerTurn(Dealer dealer, DeckStrategy deck) {
		while(dealer.isRunning()) {
			dealer.addCard(deck.distributeCard());
			outputView.displayDealerUnderSevenTeen();
		}
	}

	private void makeResult2(Players players, Dealer dealer) {
		// outputView.displayAllCardAndScore(dealer.getName(), dealer.getScore(), dealer.getCards());
		// for (Player player : players.getPlayers()) {
		// 	outputView.displayAllCardAndScore(player.getName(), player.getScore(), player.getCards());
		// }
		// Result result = new Result();
		// Map<Player, ResultType> gameResult = result.getResult(players.getPlayers(), dealer);
		// outputView.displayResult(gameResult);
	}

	private void makeResult(Players players, Dealer dealer) {
		outputView.displayAllCardAndScore(dealer.getName(), dealer.getScore(), dealer.getCards());
		for (Player player : players.getPlayers()) {
			outputView.displayAllCardAndScore(player.getName(), player.getScore(), player.getCards());
		}
		final BettingBox bettingBox = new BettingBox(players.getPlayers(), dealer);
		outputView.displayProfitResult(bettingBox.playerProfit(), bettingBox.dealerProfit());
	}
}
