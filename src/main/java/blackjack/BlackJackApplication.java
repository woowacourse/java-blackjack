package blackjack;

import java.util.List;

import blackjack.domain.RedrawChoice;
import blackjack.dto.PlayerTurnDto;
import blackjack.dto.TableStatusDto;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

	public static void main(String[] args) {
		final BlackJackService blackJack = new BlackJackService();
		blackJack.initBlackJack();
		blackJack.joinPlayers(InputView.requestPlayerName());
		TableStatusDto dealerStatus = blackJack.distributeCardToDealer();
		List<TableStatusDto> playersStatus = blackJack.distributeCardToPlayers();
		OutputView.printInitialStatus(dealerStatus, playersStatus);

		while (true) {
			PlayerTurnDto currentPlayer = blackJack.whoseTurn();
			if (hasMorePlayer(currentPlayer)) {
				break;
			}
			String answer = InputView.drawOneMoreCard(currentPlayer);
			TableStatusDto playerStatus = blackJack.drawPlayer(RedrawChoice.of(answer),
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
