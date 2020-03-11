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
	public static void run() {
		List<Player> players = PlayersFactory.of(InputView.inputUserNames());
		Dealer dealer = new Dealer();
		Deck deck = new Deck();

		for (Player player : players) {
			player.hit(deck.drawCard());
			player.hit(deck.drawCard());
		}
		dealer.hit(deck.drawCard());
		dealer.hit(deck.drawCard());
		OutputView.printReceivedCards(players, dealer);

		for (Player player : players) {
			while (player.canHit() && YesOrNo.of(InputView.inputReceiveMore(player)).isYes()) {
				player.hit(deck.drawCard());
				OutputView.printCards(player);
			}
		}
		while (dealer.canHit()) {
			dealer.hit(deck.drawCard());
			OutputView.printDealerCards(dealer);
		}

		GameResult gameResult = GameResult.from(players, dealer);
		OutputView.printDealerResult(gameResult.dealerResult());
		OutputView.printPlayersResult(gameResult);
	}
}