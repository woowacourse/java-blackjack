package blackjack;

import java.util.List;

import blackjack.domain.RedrawChoice;
import blackjack.domain.role.Role;
import blackjack.dto.PlayerTurnDto;
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
			PlayerTurnDto currentPlayer = blackJack.whoseTurn();
			if (hasMorePlayer(currentPlayer)) {
				break;
			}
			String answer = InputView.drawOneMoreCard(currentPlayer);
			final Role playerStatus = blackJack.drawPlayer(RedrawChoice.of(answer),
				currentPlayer.getName());
			OutputView.printPersonalHand(playerStatus);
		}
		OutputView.printDealerStatus(blackJack.drawDealer());
		OutputView.printFinalResult(blackJack.calculateFinalResult());

	}

	private static boolean hasMorePlayer(PlayerTurnDto player) {
		return player.getName().isEmpty();
	}
}
