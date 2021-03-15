package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
	public static final String ERROR_MESSAGE_CALL = "옳지 않은 곳에서 호츨";
	public static final String ERROR_MESSAGE_INPUT = "옳지 않은 입력입니다.";

	public void run() {
		Dealer dealer = new Dealer();
		Players players = askPlayers(dealer);
		Deck deck = new Deck(new DeckGenerator().makeCards());

		playTurn(dealer, players, deck);

		OutputView.noticePlayersPoint(dealer, players);
		OutputView.printDealerResult(players.calculateProfits(dealer));
		OutputView.printPlayersResult(players);
	}

	private Players askPlayers(Dealer dealer) {
		try {
			return new Players(InputView.enterNames(), dealer);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return askPlayers(dealer);
		}
	}

	private void createPlayersWithMoney(Players players) {
		try {
			askPlayerMoney(players);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			askPlayerMoney(players);
		}
	}

	private void askPlayerMoney(Players players) {
		for (Player player : players.toList()) {
			player.makeProfit(askMoney(player));
		}
	}

	private int askMoney(Player player) {
		try {
			return Integer.parseInt(InputView.enterBetting(player));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_INPUT);
		}
	}

	private void playTurn(Dealer dealer, Players players, Deck deck) {
		createPlayersWithMoney(players);

		drawCards(dealer, players, deck);
		drawUntilPossible(dealer, players, deck);
	}

	private void drawCards(Dealer dealer, Players players, Deck deck) {
		OutputView.noticeDrawTwoCards(players);
		players.initialCards(deck);
		OutputView.noticePlayersCards(dealer, players);
	}

	private void drawUntilPossible(Dealer dealer, Players players, Deck deck) {
		for (Player player : players.toList()) {
			askKeepDrawing(player, deck);
		}
		while (dealer.canReceiveCard(true)) {
			dealer.receiveCard(deck.dealCard());
			OutputView.noticeDealerReceiveCard();
		}
	}

	private void askKeepDrawing(Player player, Deck deck) {
		try {
			playEachPlayer(player, deck);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			playEachPlayer(player, deck);
		}
	}

	private void playEachPlayer(Player player, Deck deck) {
		while (player.canReceiveCard(InputView.isContinueDraw(player))) {
			player.receiveCard(deck.dealCard());
			OutputView.noticePlayerCards(player);
		}
	}
}
