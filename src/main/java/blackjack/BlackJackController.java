package blackjack;

import blackjack.domain.BlackJack;
import blackjack.domain.BlackJackDto;
import blackjack.domain.Participant;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class BlackJackController {

	public void run() {
		BlackJack blackJack = BlackJack.createFrom(InputView.askPlayerNameInput());
		BlackJackDto blackJackDto = BlackJackDto.from(blackJack);

		startGame(blackJack, blackJackDto);
		decidePlayersReceivingAdditionalCard(blackJack, blackJackDto);
		decideDealerReceivingAdditionalCard(blackJack, blackJackDto);
		finishGame(blackJack, blackJackDto);
	}

	private void startGame(BlackJack blackJack, BlackJackDto blackJackDto) {
		blackJack.handOutStartingCards();
		ResultView.showStartingStatus(blackJackDto);
	}

	private void decidePlayersReceivingAdditionalCard(BlackJack blackJack, BlackJackDto blackJackDto) {
		for (Participant player : blackJack.getPlayers()) {
			while (InputView.askAdditionalCardInput(player.getName())) {
				blackJack.handOutCardTo(player);
				ResultView.showEachPlayerCurrentStatus(blackJackDto, player);
			}
			ResultView.showEachPlayerCurrentStatus(blackJackDto, player);
		}
	}

	private void decideDealerReceivingAdditionalCard(BlackJack blackJack, BlackJackDto blackJackDto) {
		boolean dealerNeedAdditionalCard = blackJack.isDealerNeedAdditionalCard();
		if (dealerNeedAdditionalCard) {
			blackJack.handOutCardTo(blackJackDto.getDealer());
		}
		ResultView.showWhetherDealerReceivedOrNot(dealerNeedAdditionalCard);
	}

	private void finishGame(BlackJack blackJack, BlackJackDto blackJackDto) {
		ResultView.showFinalStatus(blackJackDto);
		blackJack.calculateResult();
		ResultView.showResult(blackJackDto);
	}
}
