package blackjack;

import blackjack.dto.DealerTableDto;
import blackjack.dto.PlayerTableDto;
import java.util.List;

import blackjack.controller.BlackJackController;
import blackjack.dto.PlayerStatusDto;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

	public static void main(String[] args) {
		BlackJackController controller = new BlackJackController(new BlackJackService());

		controller.initBlackJackGame();
		controller.addPlayers(InputView.requestPlayerName());

		DealerTableDto dealerTable = controller.distributeCardToDealer();
		List<PlayerTableDto> playersTable = controller.distributeCardToPlayers();
		OutputView.printInitialStatus(dealerTable, playersTable);

		while (true) {
			String answer = InputView.drawOneMoreCard(controller.getWhoseTurn());
			PlayerStatusDto playerStatus = controller.drawPlayer(answer);
			OutputView.printPlayerHand(playerStatus.getTableStatusDto());
			if (playerStatus.isDraw()) {
				continue;
			}
			if (!playerStatus.isHasNextPlayer()) {
				break;
			}
		}
		OutputView.printDealerStatus(controller.drawDealer());
		OutputView.printFinalResult(controller.getFinalResult());
	}
}
