package blackjack.controller;

import blackjack.domain.machine.Blackjack;
import blackjack.domain.participant.Player;
import blackjack.domain.strategy.RandomNumberGenerator;
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
		blackjack.dealInitialCards(RandomNumberGenerator.getInstance());

		OutputView.printInitStatus(blackjack.getDealer(), blackjack.getPlayers());
	}

	private void progressBlackjack() {
		while (blackjack.isDealOneMore()) {
			askDealCard(blackjack.getNextPlayer());
		}

		if (blackjack.dealAdditionalCardToDealer(RandomNumberGenerator.getInstance())) {
			OutputView.printDealerAdditionalCard();
		}
	}

	private void askDealCard(Player player) {
		while (!blackjack.isPlayerBurst(player) && InputView.askAdditionalCard(player)) {
			blackjack.dealAdditionalCardToPlayer(RandomNumberGenerator.getInstance(), player);
			OutputView.printCards(blackjack.findPlayer(player));
		}
	}

	private void endBlackjack() {
		OutputView.printCardsAndResult(blackjack.getDealer(), blackjack.getPlayers());
		OutputView.printResult(blackjack.result());
	}
}
