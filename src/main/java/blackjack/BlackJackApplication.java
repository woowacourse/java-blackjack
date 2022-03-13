package blackjack;

import java.util.List;

import blackjack.controller.BlackJackController;
import blackjack.dto.PlayerTurnDto;
import blackjack.dto.TableStatusDto;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

	public static void main(String[] args) {
		BlackJackController controller = new BlackJackController(new BlackJackService());

		controller.addPlayers(InputView.requestPlayerName());

		TableStatusDto dealerStatus = controller.distributeCardToDealer();
		List<TableStatusDto> playersStatus = controller.distributeCardToPlayers();
		OutputView.printInitialStatus(dealerStatus, playersStatus);

		while (true) {
			PlayerTurnDto currentPlayer = controller.getWhoseTurn();
			if (hasMorePlayer(currentPlayer)) {
				break;
			}
			String answer = InputView.drawOneMoreCard(currentPlayer);
			TableStatusDto playerStatus = controller.drawPlayer(answer, currentPlayer.getName());
			OutputView.printPersonalHand(playerStatus);
		}
		OutputView.printDealerStatus(controller.drawDealer());
		OutputView.printFinalResult(controller.getFinalResult());
	}

	private static boolean hasMorePlayer(PlayerTurnDto player) {
		return player.getName().isEmpty();
	}
}
