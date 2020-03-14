package controller;

import domain.card.Deck;
import domain.gamer.*;
import domain.result.GameResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {
	private static final int FIRST_GIVE_CARD_COUNT = 2;
	private final Gamers gamers;
	private final Deck deck;

	public BlackJackController() {
		List<Player> players = PlayersFactory.newPlayers(InputView.inputUserNames());
		Dealer dealer = new Dealer();
		this.deck = new Deck();
		this.gamers = new Gamers(players, dealer);
	}

	public void run() {
		init();
		giveCardToPlayers();
		giveCardToDealer();

		GameResult gameResult = new GameResult(gamers);
		OutputView.printCardsAndScore(gameResult.gamersScore());
		OutputView.printDealerResult(gameResult.dealerResult());
		OutputView.printPlayersResult(gameResult.playersResult());
	}

	private void init() {
		gamers.giveCardToAll(deck, FIRST_GIVE_CARD_COUNT);
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