package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.DealerDrawable;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.List;

import blackjack.controller.BlackJackController;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.TableStatusDto;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackApplication {

	public static void main(String[] args) {
		BlackJackController controller = new BlackJackController(new BlackJackService());

		controller.initBlackJackGame();
		controller.addPlayers(InputView.requestPlayerName());

		TableStatusDto dealerStatus = controller.distributeCardToDealer();
		List<TableStatusDto> playersStatus = controller.distributeCardToPlayers();
		OutputView.printInitialStatus(dealerStatus, playersStatus);

		while (true) {
			String answer = InputView.drawOneMoreCard(controller.getWhoseTurn());
			PlayerStatusDto playerStatus = controller.drawPlayer(answer);
			OutputView.printPersonalHand(playerStatus.getTableStatusDto());
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
