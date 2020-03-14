package controller;

import domain.card.Deck;
import domain.gamer.*;
import domain.result.GameResult;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
	private final List<Gamer> gamers;
	private final List<Player> players;
	private final Dealer dealer;
	private final Deck deck;

	public BlackJackController() {
		this.players = PlayersFactory.newPlayers(InputView.inputUserNames());
		this.dealer = new Dealer();
		this.deck = new Deck();
		this.gamers = createGamers();
	}

	private List<Gamer> createGamers() {
		List<Gamer> gamers = new ArrayList<>();
		gamers.add(dealer);
		gamers.addAll(players);

		return gamers;
	}

	public void run() {
		giveTwoCards();
		giveCardToPlayers();
		giveCardToDealer();
		OutputView.printCardsAndScore(gamers);

		GameResult gameResult = GameResult.of(players, dealer);
		OutputView.printDealerResult(gameResult.dealerResult());
		OutputView.printPlayersResult(gameResult);
	}

	private void giveTwoCards() {
		for (Gamer gamer : gamers) {
			gamer.hit(deck.drawCard());
			gamer.hit(deck.drawCard());
		}
		OutputView.printGiving(players, dealer);
		OutputView.printFirstOpenedCards(gamers);
	}

	private void giveCardToPlayers() {
		for (Player player : players) {
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
		while (dealer.canHit()) {
			dealer.hit(deck.drawCard());
			OutputView.printDealerCards();
		}
	}
}