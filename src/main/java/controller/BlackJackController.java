package controller;

import java.util.List;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayersFactory;
import domain.participant.YesOrNo;
import domain.result.GameResult;
import view.InputView;
import view.OutputView;

public class BlackJackController {
	private final List<Player> players;
	private final Dealer dealer;
	private final Deck deck;

	public BlackJackController() {
		this.players = PlayersFactory.of(InputView.inputUserNames());
		this.dealer = new Dealer();
		this.deck = new Deck();
	}

	public void run() {
		giveTwoCards();
		giveCardToPlayers();
		giveCardToDealer();
		OutputView.printCardsScore(players, dealer);

		GameResult gameResult = GameResult.from(players, dealer);
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
		while (player.canHit() && YesOrNo.of(InputView.inputReceiveMore(player)).isYes()) {
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