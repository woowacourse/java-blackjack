package blackjack;

import java.util.List;

import blackjack.domain.RedrawChoice;
import blackjack.domain.role.Role;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

	public static void main(String[] args) {
		final BlackJackService blackJack = new BlackJackService();
		blackJack.initBlackJack();
		blackJack.joinPlayers(InputView.requestPlayerName());
		final Role dealerStatus = blackJack.distributeCardToDealer();
		List<Role> playersStatus = blackJack.distributeCardToPlayers();
		OutputView.printInitialStatus(dealerStatus, playersStatus);

		while (true) {
			final String currentPlayer = blackJack.whoseTurn();
			if (currentPlayer.isEmpty()) {
				break;
			}
			String answer = InputView.drawOneMoreCard(currentPlayer);
			final Role playerStatus = blackJack.drawPlayer(RedrawChoice.of(answer), currentPlayer);
			OutputView.printPersonalHand(playerStatus);
		}
		OutputView.printDealerStatus(blackJack.drawDealer());
		OutputView.printFinalResult(blackJack.calculateFinalResult());

	}

}
