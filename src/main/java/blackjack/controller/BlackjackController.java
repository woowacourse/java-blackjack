package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.Player;
import blackjack.domain.RandomNumberGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

	public void run() {
		List<String> playerNames = InputView.getPlayerNames();
		Blackjack blackjack = new Blackjack(playerNames);
		blackjack.distributeInitialCards(RandomNumberGenerator.getInstance());

		OutputView.printInitStatus(blackjack.getDealer(), blackjack.getPlayers());

		while (blackjack.isDistributeMore()) {
			Player player = blackjack.getPlayer();

			while (InputView.askAdditionalCard(player)) {
				blackjack.distributeAdditionalCardPlayer(RandomNumberGenerator.getInstance(), player);
				OutputView.printCards(blackjack.findPlayer(player));
			}
		}

		blackjack.distributeAdditionalCardDealer(RandomNumberGenerator.getInstance());
		OutputView.printDealerAdditionalCard();

		OutputView.printCardsAndResult(blackjack.getDealer(), blackjack.getPlayers());
		OutputView.printResult(blackjack.result());
	}
}
