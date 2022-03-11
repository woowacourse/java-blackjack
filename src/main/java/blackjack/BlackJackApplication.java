package blackjack;

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

		controller.addPlayers(InputView.requestPlayerName());

		TableStatusDto dealerStatus = controller.distributeCardToDealer();
		List<TableStatusDto> playersStatus = controller.distributeCardToPlayers();
		OutputView.printInitialStatus(dealerStatus, playersStatus);

		// 결과 파산시 파산이ㅡ로 리턴
		// 딜러 빅토리 해결
		// 출력 개행 및 스페이스바
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
