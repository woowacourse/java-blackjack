package controller;

import domain.card.Deck;
import domain.gamer.*;
import domain.result.GameResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {
	private final Gamers gamers;
	private final Deck deck;

	public BlackJackController() {
		List<Player> players = PlayersFactory.newPlayers(InputView.inputUserNames());
		Dealer dealer = new Dealer();
		this.deck = new Deck();
		this.gamers = new Gamers(players, dealer);
	}

	public void run() {
		giveTwoCards();
		giveCardToPlayers();
		giveCardToDealer();

		GameResult gameResult = new GameResult(gamers);
		OutputView.printCardsAndScore(gameResult.gamersScore());
		OutputView.printDealerResult(gameResult.dealerResult());
		OutputView.printPlayersResult(gameResult.playersResult());
	}

	private void giveTwoCards() {
		gamers.giveCardToAll(deck);
		gamers.giveCardToAll(deck);
		OutputView.printGiving(gamers);
		OutputView.printFirstOpenedCards(gamers);
	}

	private void giveCardToPlayers() {
		for (Player player : gamers.getPlayers()) {
			giveCardToPlayer(player);
		}
	}

	private void giveCardToPlayer(Player player) {
		while (player.canHit() && Answer.from(InputView.inputReceiveMore(player)).isYes()) {
			player.hit(deck.drawCard());
			OutputView.printCards(player);
		}
	}

	private void giveCardToDealer() {
		while (gamers.getDealer().canHit()) {
			gamers.getDealer().hit(deck.drawCard());
			OutputView.printDealerCards();
		}
	}
}