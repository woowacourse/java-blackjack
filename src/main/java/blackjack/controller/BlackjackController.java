package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.Player;
import blackjack.domain.RandomNumberGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
	private Blackjack blackjack;

	public void run() {
		startBlackJack();
		progressBlackjack();
		endBlackjack();
	}

	private void startBlackJack() {
		List<String> playerNames = InputView.getPlayerNames();
		blackjack = new Blackjack(playerNames);
		blackjack.distributeInitialCards(RandomNumberGenerator.getInstance());

		OutputView.printInitStatus(blackjack.getDealer(), blackjack.getPlayers());
	}

	private void progressBlackjack() {
		while (blackjack.isDistributeMore()) {
			askDistributingCard(blackjack.getNextPlayer());
		}
	}

	private void askDistributingCard(Player player) {
		while (InputView.askAdditionalCard(player)) {
			blackjack.distributeAdditionalCardToPlayer(RandomNumberGenerator.getInstance(), player);
			OutputView.printCards(blackjack.findPlayer(player));
		}

		blackjack.distributeAdditionalCardToDealer(RandomNumberGenerator.getInstance());
		OutputView.printDealerAdditionalCard();
	}

	private void endBlackjack() {
		OutputView.printCardsAndResult(blackjack.getDealer(), blackjack.getPlayers());
		OutputView.printResult(blackjack.result());
	}
}
