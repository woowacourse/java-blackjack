package controller;

import java.util.List;

import domain.card.Deck;
import domain.gamer.Answer;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.PlayersFactory;
import domain.result.GameResult;
import view.InputView;
import view.OutputView;

public class BlackJackController {
	private final List<Player> players;
	private final Dealer dealer;
	private final Deck deck;

	public BlackJackController() {
		this.players = PlayersFactory.create(InputView.inputUserNames());
		this.dealer = new Dealer();
		this.deck = new Deck();
	}

	public void run() {
		giveTwoCards();
		giveCardToPlayers();
		giveCardToDealer();
		OutputView.printCardsScore(players, dealer);

		GameResult gameResult = GameResult.of(players, dealer);
		OutputView.printDealerResult(gameResult.dealerResult());
		OutputView.printPlayersResult(gameResult);
	}

	private void giveTwoCards() {
		for (Player player : players) {
			player.hit(deck.drawCard());
			player.hit(deck.drawCard());
		}
		dealer.hit(deck.drawCard());
		dealer.hit(deck.drawCard());
		OutputView.printGiving(players, dealer);
		OutputView.printDealerCard(dealer);
		OutputView.printPlayersCard(players);
	}

	private void giveCardToPlayers() {
		for (Player player : players) {
			giveCardToPlayer(player);
		}
	}

	private void giveCardToPlayer(Player player) {
		while (player.canHit() && Answer.of(InputView.inputReceiveMore(player)).isYes()) {
			player.hit(deck.drawCard());
			OutputView.printCards(player);
		}
	}

	private void giveCardToDealer() {
		while (dealer.canHit()) {
			dealer.hit(deck.drawCard());
			OutputView.printDealerCards();
		}
	}
}