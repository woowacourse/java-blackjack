package blackjack.controller;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.result.Profit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGameController {

	private final InputView inputView;
	private final OutputView outputView;
	private final Deck deck;

	public BlackJackGameController(InputView inputView, OutputView outputView, Deck deck) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.deck = deck;
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
			final List<String> playerNames = inputView.inputPlayerNames();
			final List<Player> players = addPlayers(playerNames);
			return new Players(players);
		} catch (IllegalArgumentException exception) {
			outputView.printException(exception.getMessage());
			return generatePlayers();
		}
	}

	private List<Player> addPlayers(List<String> playerNames) {
		final List<Player> players = new ArrayList<>();
		for (String playerName : playerNames) {
			final double money = inputView.inputMoney(playerName);
			players.add(new Player(playerName, money, deck));
		}
		return players;
	}

	private void printInitialCards(Players players, Dealer dealer) {
		outputView.displayFirstDistribution(players.getPlayers());
		outputView.displayOneCard(dealer.getCards().get(0));
		for (Player player : players.getPlayers()){
			outputView.displayAllCard(player.getName(), player.getCards());
		}
	}

	private void progressPlayerTurn(Players players, Deck deck) {
		for (Player player : players.getPlayers()) {
			progressOnePlayer(deck, player);
		}
	}

	private void progressOnePlayer(Deck deck, Player player) {
		while (player.isRunning() && decidePlayerHit(player)) {
			player.addCard(deck.distributeCard());
			outputView.displayAllCard(player.getName(), player.state().getCards().getCards());
		}
	}

	private boolean decidePlayerHit(Player player) {
		if (!inputView.isHitDecision(player.getName())) {
			player.stay();
			return false;
		}
		return true;
	}

	private void progressDealerTurn(Dealer dealer, Deck deck) {
		while(dealer.isRunning() && isDealerHit(dealer)) {
			dealer.addCard(deck.distributeCard());
			outputView.displayDealerUnderSevenTeen();
		}
	}

	private boolean isDealerHit(Dealer dealer) {
		if (!dealer.isHit()) {
			dealer.stay();
			return false;
		}
		return true;
	}

	private void makeResult(Players players, Dealer dealer) {
		outputView.displayAllCardAndScore(dealer.getName(), dealer.getScore(), dealer.getCards());
		for (Player player : players.getPlayers()) {
			outputView.displayAllCardAndScore(player.getName(), player.getScore(), player.getCards());
		}
		final Profit profit = new Profit(players.getPlayers(), dealer);
		outputView.displayProfitResult(profit.playerProfit(), profit.dealerProfit());
	}
}
